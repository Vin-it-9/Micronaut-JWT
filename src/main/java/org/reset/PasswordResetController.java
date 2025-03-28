package org.reset;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import jakarta.inject.Inject;



import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller("/web")
@Secured(SecurityRule.IS_ANONYMOUS)
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @Inject
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Get("/forgot-password")
    @View("forgot-password")
    public Map<String, Object> forgotPasswordForm() {
        return new HashMap<>();
    }

    @Post("/forgot-password")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @View("forgot-password-confirmation")
    public Map<String, Object> processForgotPassword(String email) {
        Map<String, Object> model = new HashMap<>();

        model.put("email", email);

        // Try to create reset token and show it directly (for development)
        passwordResetService.createPasswordResetTokenForUser(email)
                .ifPresentOrElse(
                        resetUrl -> {
                            // Show the reset link directly on the page
                            model.put("success", true);
                            model.put("resetUrl", resetUrl);
                        },
                        () -> {
                            // User not found, but don't reveal this (security)
                            model.put("success", false);
                        }
                );

        return model;
    }

    @Get("/reset-password")
    @View("reset-password")
    public Map<String, Object> resetPasswordForm(@QueryValue String token) {
        Map<String, Object> model = new HashMap<>();

        boolean validToken = passwordResetService.validatePasswordResetToken(token);
        model.put("validToken", validToken);
        model.put("token", token);

        return model;
    }

    @Post("/reset-password")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> processResetPassword(String token, String newPassword, String confirmPassword) {
        boolean success = passwordResetService.resetPassword(token, newPassword);

        if (success) {
            return HttpResponse.redirect(URI.create("/web/login?reset=success"));
        } else {
            return HttpResponse.redirect(URI.create("/web/reset-password?token=" +
                    token + "&error=true"));
        }
    }
}