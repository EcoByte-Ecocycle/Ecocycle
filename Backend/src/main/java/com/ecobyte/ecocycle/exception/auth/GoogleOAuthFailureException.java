package com.ecobyte.ecocycle.exception.auth;

public class GoogleOAuthFailureException extends RuntimeException {
    private final String message;

    public GoogleOAuthFailureException(final String message) {
        this.message = message;
    }
}
