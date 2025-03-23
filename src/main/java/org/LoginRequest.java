package org;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Introspected
@Serdeable
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}