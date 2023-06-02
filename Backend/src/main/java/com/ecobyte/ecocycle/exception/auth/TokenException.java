package com.ecobyte.ecocycle.exception.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenException extends RuntimeException {

    private final HttpStatus httpStatus;
    protected String message;

    public TokenException(final String message) {
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.message = message;
    }
}
