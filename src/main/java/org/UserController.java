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
    public Map<String, Object> getUserProfile(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("roles", authentication.getRoles());
        response.put("attributes", authentication.getAttributes());
        return response;
    }

}