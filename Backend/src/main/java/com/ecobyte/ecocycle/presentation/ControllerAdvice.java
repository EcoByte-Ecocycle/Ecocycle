package com.ecobyte.ecocycle.presentation;

import com.ecobyte.ecocycle.dto.response.ErrorResponse;
import com.ecobyte.ecocycle.exception.UserNotFoundException;
import com.ecobyte.ecocycle.exception.auth.TokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ErrorResponse> handleExpiredTokenException(final TokenException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpiredTokenException(final UserNotFoundException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(new ErrorResponse(exception.getMessage()));
    }
}
