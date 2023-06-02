package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidImageUrlException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public InvalidImageUrlException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "이미지를 다시 첨부해주세요";
    }
}
