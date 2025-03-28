package org.security;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
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
                secret != null ? secret : "aB3#F8eZ!2jL0sV9mX1pQ6rT5wN7uK4dY2zC8gH3oJ0lR5iM1nU4vW7xE9yP0Z#1"
        );
    }


}