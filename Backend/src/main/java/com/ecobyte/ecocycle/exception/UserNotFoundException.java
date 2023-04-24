package com.ecobyte.ecocycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "사용자가 없습니다.";

    private final HttpStatus httpStatus;
    private final String message;

    public UserNotFoundException() {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = USER_NOT_FOUND_ERROR_MESSAGE;
    }
}
