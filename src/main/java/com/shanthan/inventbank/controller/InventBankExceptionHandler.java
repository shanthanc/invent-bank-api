package com.shanthan.inventbank.controller;

import com.shanthan.inventbank.exception.InventBankException;
import com.shanthan.inventbank.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class InventBankExceptionHandler {

    @ExceptionHandler(InventBankException.class)
    public ResponseEntity<ErrorResponse> appException(InventBankException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(ErrorResponse.builder()
                        .httpStatus(exception.getHttpStatus())
                        .errorMessage(exception.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalException(Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                        .httpStatus(INTERNAL_SERVER_ERROR)
                        .errorMessage(exception.getMessage())
                .build());
    }
}
