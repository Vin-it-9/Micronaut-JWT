package org.auth;

import com.nimbusds.jose.JWSAlgorithm;
import io.micronaut.context.annotation.*;
import io.micronaut.security.token.jwt.signature.secret.SecretSignatureConfiguration;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Factory
public class SecurityConfig {

    @Singleton
    @Primary
    @Replaces(SecretSignatureConfiguration.class)
    public SecretSignatureConfiguration secretSignatureConfiguration() {
        return new SecretSignatureConfiguration("4fP7jL$Kx8!dVb@tQy9&eZsG3*mRwC6^hA2#nW5fUvS%qE1zXcB0pT_iY+aD}oM{NuJr-Hk=gI|l2O.F,vZ/xL;wK~pQm?j4R>d7U<6tG8sCb3n5aE9hWy!@#$%^&*()VfXzSqNpMgJkHrTeDyLvBcZoAi2U1w");
    }


}