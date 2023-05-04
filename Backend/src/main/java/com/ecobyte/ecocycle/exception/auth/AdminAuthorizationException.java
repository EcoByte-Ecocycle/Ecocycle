package com.ecobyte.ecocycle.exception.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AdminAuthorizationException extends RuntimeException {

    private static final String ADMIN_AUTHORIZATION_ERROR = "관리자 권한이 없습니다.";
    private final HttpStatus httpStatus;
    private final String message;

    public AdminAuthorizationException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = ADMIN_AUTHORIZATION_ERROR;
    }
}
