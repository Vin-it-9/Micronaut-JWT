package org.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserProfileDTO {

    private String username;
    private String email;
    private String userId;
    private LocalDateTime lastLogin;
    private Set<String> roles;
    private String status;

    public UserProfileDTO(String username, String email, String userId, LocalDateTime lastLogin, Set<String> roles, String status) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.lastLogin = lastLogin;
        this.roles = roles;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}