package org;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;

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