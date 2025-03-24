package org;

import io.micronaut.context.annotation.*;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;

import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {


    @Value("${micronaut.security.token.jwt.signatures.secret.generator.secret}")
    private String jwtSecret;

//    @Singleton
//    public SecretSignatureConfiguration secretSignatureConfiguration() {
//        return new SecretSignatureConfiguration(jwtSecret);
//    }

    @Singleton
    @Primary
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        if (jwtSecret == null || jwtSecret.trim().isEmpty()) {
            jwtSecret = "abcdef0123456789abcdef0123456789";
            System.out.println("WARNING: Using default JWT secret! Set JWT_SECRET environment variable.");
        }
        return new SecretSignatureConfiguration(jwtSecret);
    }

}