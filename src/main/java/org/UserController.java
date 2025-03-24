package org;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.authentication.Authentication;

import java.util.HashMap;
import java.util.Map;

@Controller("/api/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Get("/me")
    public Map<String, Object> getUserInfo(Authentication authentication) {
        System.out.println("User info requested for: " + authentication.getName());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("roles", authentication.getRoles());

        Map<String, Object> attributes = authentication.getAttributes();
        if (attributes.containsKey("email")) {
            userInfo.put("email", attributes.get("email"));
        }

        return userInfo;
    }
}