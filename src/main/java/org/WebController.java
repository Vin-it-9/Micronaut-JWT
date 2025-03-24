package org;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import java.util.HashMap;
import java.util.Map;

@Controller("/web")
public class WebController {

    @Get("/login")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("login")
    public Map<String, Object> login() {
        return new HashMap<>();
    }

    @Get("/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("register")
    public Map<String, Object> register() {
        return new HashMap<>();
    }

    @Get("/dashboard")
    @View("dashboard")
    public Map<String, Object> dashboard() {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Dashboard");
        return model;
    }


}