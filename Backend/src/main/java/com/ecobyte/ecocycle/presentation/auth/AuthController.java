package com.ecobyte.ecocycle.presentation.auth;

import com.ecobyte.ecocycle.application.auth.AuthService;
import com.ecobyte.ecocycle.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestParam("code") final String code) {
        final LoginResponse loginResponse = authService.loginWithGoogle(code);
        return ResponseEntity.ok(loginResponse);
    }
}
