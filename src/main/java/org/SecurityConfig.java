package org;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;

import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {


    @Value("${micronaut.security.token.jwt.signatures.secret.generator.secret}")
    private String jwtSecret;

    @Singleton
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        return new SecretSignatureConfiguration(jwtSecret);
    }


}