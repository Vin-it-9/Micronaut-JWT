package org.security;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Singleton;

@Factory
public class SimpleJwtConfig {

    @Singleton
    @Primary
    @Replaces(SecretSignatureConfiguration.class)
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        return new SecretSignatureConfiguration("aB3#F8eZ!2jL0sV9mX1pQ6rT5wN7uK4dY2zC8gH3oJ0lR5iM1nU4vW7xE9yP0Z#1");
    }
}