package com.ecobyte.ecocycle.exception.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenException extends RuntimeException {

    protected String message;
    private final HttpStatus httpStatus;

    public TokenException(final String message) {
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.message = message;
    }
}
