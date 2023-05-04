package com.ecobyte.ecocycle.application.auth;

import com.ecobyte.ecocycle.exception.auth.AdminAuthorizationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminAspect {

    private final AuthService authService;

    public AdminAspect(final AuthService authService) {
        this.authService = authService;
    }

    @Before("@annotation(com.ecobyte.ecocycle.application.auth.AdminAuthorization) && args(loginId)")
    public void authorizeAdmin(final Long loginId) {
        if (!authService.isAdmin(loginId)) {
            throw new AdminAuthorizationException();
        }
    }
}
