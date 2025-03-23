package org;

import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Singleton
public class PasswordEncoder {

    private final BCryptPasswordEncoder delegate = new BCryptPasswordEncoder();

    public String encode(String rawPassword) {
        return delegate.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}