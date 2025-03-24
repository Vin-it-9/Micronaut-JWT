package org;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {

    @Value("${JWT_SECRET:abcdef0123456789abcdef0123456789}")
    private String jwtSecret;

    @Singleton
    @Primary
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        System.out.println("Configuring JWT with secret: " + (jwtSecret != null ? "[SECRET PRESENT]" : "[SECRET MISSING]"));
        if (jwtSecret == null || jwtSecret.trim().isEmpty()) {
            jwtSecret = "abcdef0123456789abcdef0123456789";
        }
        return new SecretSignatureConfiguration(jwtSecret);
    }
}