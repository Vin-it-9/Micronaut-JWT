package org.controller;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;
import org.reqres.LoginRequest;
import org.reqres.RegisterRequest;
import org.reqres.TokenResponse;
import org.service.AuthenticationService;

@Controller("/api/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthController {

    private final AuthenticationService authenticationService;


    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Post("/register")
    public HttpResponse<TokenResponse> register(@Body @Valid RegisterRequest request) {
        return HttpResponse.ok(authenticationService.register(request));
    }

    @Post("/login")
    public HttpResponse<TokenResponse> login(@Body @Valid LoginRequest request) {
        return HttpResponse.ok(authenticationService.login(request));
    }




}