package org.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.dto.UserProfileDTO;
import org.service.UserService;
import java.util.*;

@Controller("/api/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Inject
    private UserService userService;


    @Get("/me")
    public UserProfileDTO getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        return userService.getUserProfile(username);
    }

    @Get("/basic-info")
    public Map<String, Object> getBasicUserInfo(Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("roles", authentication.getRoles());

        Map<String, Object> attributes = authentication.getAttributes();
        if (attributes.containsKey("email")) {
            userInfo.put("email", attributes.get("email"));
        }

        return userInfo;
    }

    /**
     * Update last login time (useful when accessing profile)
     */
    @Get("/update-login-time")
    public Map<String, String> updateLastLogin(Authentication authentication) {
        String username = authentication.getName();
        userService.updateLastLogin(username);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Last login time updated");
        return response;
    }

}