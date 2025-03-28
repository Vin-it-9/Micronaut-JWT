package org.controller;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;


import org.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Inject
    private UserService userService;


    @Get("/me")
    public Map<String, Object> getUserInfo(Authentication authentication) {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("roles", authentication.getRoles());

        Map<String, Object> attributes = authentication.getAttributes();
        if (attributes.containsKey("email")) {
            userInfo.put("email", attributes.get("email"));
        }

        return userInfo;

    }

//    @Get("/me")
//    public Map<String, Object> getCurrentUser(Authentication authentication) {
//        // Assuming the authentication object contains the necessary user information
//        String username = authentication.getName();
//        UserProfileDTO profile = userService.getUserProfile(username);
//
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("username", profile.getUsername());
//        userInfo.put("email", profile.getEmail());
//        userInfo.put("userId", profile.getUserId());
//        userInfo.put("lastLogin", profile.getLastLogin());
//        userInfo.put("roles", profile.getRoles());
//        userInfo.put("status", profile.getStatus());
//
//        return userInfo;
//    }

//    @Get("/activity")
//    public HttpResponse<List<UserActivityDTO>> getUserActivity(Authentication authentication) {
//        String username = authentication.getName();
//        List<UserActivityDTO> activities = activityService.getRecentActivity(username, 10);
//        return HttpResponse.ok(activities);
//    }

}