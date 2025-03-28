package org.reset;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import org.entity.PasswordResetToken;
import org.entity.User;
import org.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Singleton
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${micronaut.server.url:http://localhost:8080}")
    private String serverUrl;

    public PasswordResetService(
            PasswordResetTokenRepository tokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Optional<String> createPasswordResetTokenForUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Delete any existing tokens for this user
            tokenRepository.deleteByUser(user);

            // Create new token
            PasswordResetToken token = new PasswordResetToken(user);
            tokenRepository.save(token);

            // Return the reset URL
            return Optional.of(serverUrl + "/web/reset-password?token=" + token.getToken());
        }

        return Optional.empty();
    }

    @Transactional
    public boolean validatePasswordResetToken(String token) {
        return tokenRepository.findByToken(token)
                .map(resetToken -> !resetToken.isExpired())
                .orElse(false);
    }

    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOptional = tokenRepository.findByToken(token);

        if (tokenOptional.isPresent()) {
            PasswordResetToken resetToken = tokenOptional.get();

            if (resetToken.isExpired()) {
                return false;
            }

            User user = resetToken.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.update(user);

            // Delete the used token
            tokenRepository.deleteByToken(token);

            return true;
        }

        return false;
    }
}