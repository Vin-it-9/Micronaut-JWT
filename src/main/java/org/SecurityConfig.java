package org;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {

    @Singleton
    @Primary
    @Replaces(SecretSignatureConfiguration.class)
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        String secret = System.getenv("JWT_SECRET");
        return new SecretSignatureConfiguration(
                secret != null ? secret : "abcdef0123456789abcdef0123456789abcdef0123456789"
        );
    }


}