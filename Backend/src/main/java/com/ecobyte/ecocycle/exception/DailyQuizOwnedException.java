package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DailyQuizOwnedException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public DailyQuizOwnedException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "데일리 퀴즈 소유자가 아닙니다.";
    }
}
