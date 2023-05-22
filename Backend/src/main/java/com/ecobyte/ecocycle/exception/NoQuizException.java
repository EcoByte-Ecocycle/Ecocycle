package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoQuizException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public NoQuizException() {
        this.httpStatus = HttpStatus.NO_CONTENT;
        this.message = "퀴즈가 없습니다.";
    }
}
