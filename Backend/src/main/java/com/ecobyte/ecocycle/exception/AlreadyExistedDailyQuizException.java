package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistedDailyQuizException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public AlreadyExistedDailyQuizException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "이미 데일리 퀴즈를 풀었습니다.";
    }
}
