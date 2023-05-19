package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoDataException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public NoDataException() {
        this.httpStatus = HttpStatus.NO_CONTENT;
        this.message = "정보가 없습니다.";
    }
}
