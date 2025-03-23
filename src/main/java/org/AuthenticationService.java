package org;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import jakarta.inject.Singleton;
import org.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    // Standard JWT claim names as constants
    private static final String CLAIM_SUBJECT = "sub";
    private static final String CLAIM_ISSUER = "iss";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator tokenGenerator;

    public AuthenticationService(UserRepository userRepository,
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

        // Create new user
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        userRepository.save(user);

        return generateTokens(user);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new HttpStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new HttpStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return generateTokens(user);
    }

    private TokenResponse generateTokens(User user) {
        // Create claims for the JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT, user.getUsername());
        claims.put(CLAIM_ISSUER, "auth-service");
        claims.put("roles", user.getRoles());
        claims.put("email", user.getEmail());

        // Generate access token
        String accessToken = tokenGenerator.generateToken(claims).orElseThrow();

        // Generate refresh token with longer expiration
        Map<String, Object> refreshClaims = new HashMap<>(claims);
        refreshClaims.put("refresh", true);
        String refreshToken = tokenGenerator.generateToken(refreshClaims).orElseThrow();

        return new TokenResponse(accessToken, refreshToken, user.getUsername());
    }
}