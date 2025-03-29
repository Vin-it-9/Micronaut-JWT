package org.auth;

import com.nimbusds.jose.JWSAlgorithm;
import io.micronaut.context.annotation.*;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {

    private static final String DEFAULT_SECRET = "aB3#F8eZ!2jL0sV9mX1pQ6rT5wN7uK4dY2zC8gH3oJ0lR5iM1nU4vW7xE9yP0Z#1";

    @Singleton
    @Primary
    @Named("generator")
    @Replaces(SecretSignatureConfiguration.class)
    public SecretSignatureConfiguration secretSignatureConfiguration() {

        SecretSignatureConfiguration configuration = new SecretSignatureConfiguration(DEFAULT_SECRET);
        configuration.setJwsAlgorithm(JWSAlgorithm.HS256);

        return configuration;
    }
}