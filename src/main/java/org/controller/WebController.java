package org.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import java.util.HashMap;
import java.util.Map;

@Controller()
public class WebController {

    @Get("/web/login")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("login")
    public Map<String, Object> login() {
        return new HashMap<>();
    }

    @Get("/web/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("register")
    public Map<String, Object> register() {
        return new HashMap<>();
    }

    @Get("/web/dashboard")
    @View("dashboard")
    public Map<String, Object> dashboard() {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Dashboard");
        return model;
    }

    @Get("/")
    @View("index")
    public Map<String, Object> index() {
        return new HashMap<>();
    }

}