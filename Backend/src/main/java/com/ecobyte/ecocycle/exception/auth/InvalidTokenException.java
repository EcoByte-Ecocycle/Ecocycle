package com.ecobyte.ecocycle.exception.auth;

public class InvalidTokenException extends TokenException {

    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.");
    }
}
