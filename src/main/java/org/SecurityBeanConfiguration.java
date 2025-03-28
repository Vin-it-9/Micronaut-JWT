package org;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Factory
public class SecurityBeanConfiguration {

    @Bean
    @Singleton
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}