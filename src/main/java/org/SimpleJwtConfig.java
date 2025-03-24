package org;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Singleton;

@Factory
public class SimpleJwtConfig {

    @Singleton
    @Primary
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        return new SecretSignatureConfiguration("abcdef0123456789abcdef0123456789abcdef0123456789");
    }

}