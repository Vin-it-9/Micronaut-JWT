package org.service;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reqres.LoginRequest;
import org.reqres.RegisterRequest;
import org.reqres.TokenResponse;
import org.entity.User;
import org.repository.UserRepository;
import org.auth.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Singleton
public class AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private static final String CLAIM_SUBJECT = "sub";
    private static final String CLAIM_ISSUER = "iss";
    private static final String CLAIM_EXPIRATION = "exp";
    private static final String CLAIM_ISSUED_AT = "iat";

    private static final long ACCESS_TOKEN_EXPIRATION = 60;

    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator tokenGenerator;

    @Inject
    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public TokenResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        user = userRepository.save(user);
        user.updateLastLogin();

        return generateTokens(user);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new HttpStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new HttpStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        user.updateLastLogin();
        userRepository.update(user);

        return generateTokens(user);
    }

    private TokenResponse generateTokens(User user) {

        Instant now = Instant.now();

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT, user.getUsername());
        claims.put(CLAIM_ISSUER, "auth-service");
        claims.put(CLAIM_ISSUED_AT, now.getEpochSecond());
        claims.put("roles", user.getRoles());
        claims.put("email", user.getEmail());
        claims.put("userId", user.getUserId());

        claims.put(CLAIM_EXPIRATION, now.plus(ACCESS_TOKEN_EXPIRATION, ChronoUnit.MINUTES).getEpochSecond());
        String accessToken = tokenGenerator.generateToken(claims)
                .orElseThrow(() -> new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate access token"));

        claims.put("refresh", true);
        claims.put(CLAIM_EXPIRATION, now.plus(REFRESH_TOKEN_EXPIRATION, ChronoUnit.MINUTES).getEpochSecond());
        String refreshToken = tokenGenerator.generateToken(claims)
                .orElseThrow(() -> new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate refresh token"));

        LOG.debug("Generated JWT tokens for user: {}", user.getUsername());

        return new TokenResponse(accessToken, refreshToken, user.getUsername());
    }
}