package com.ecobyte.ecocycle.exception.auth;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super("만료된 토큰입니다.");
    }
}
