package org.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.util.Set;


@Serdeable
public class UserProfileDTO {

    private String username;
    private String email;
    private String userId;
    private LocalDateTime lastLogin;
    private Set<String> roles;
    private LocalDateTime createdAt;

    public UserProfileDTO(String username, String email, String userId, LocalDateTime lastLogin, Set<String> roles, LocalDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.lastLogin = lastLogin;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public Set<String> getRoles() {
        return roles;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}