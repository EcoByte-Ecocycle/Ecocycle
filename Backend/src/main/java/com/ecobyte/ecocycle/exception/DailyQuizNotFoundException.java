package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DailyQuizNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public DailyQuizNotFoundException() {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = "데일리 퀴즈 정보가 없습니다.";
    }
}
