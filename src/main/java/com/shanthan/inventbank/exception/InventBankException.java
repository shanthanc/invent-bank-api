package com.shanthan.inventbank.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
@Slf4j
public class InventBankException extends Exception{

    private HttpStatus httpStatus;

    private String message;

    public InventBankException() {
        super();
    }
    public InventBankException(String message) {
        super(message);
        this.message = message;
        this.httpStatus = null;

    }

    public InventBankException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public InventBankException(HttpStatus httpStatus, String message, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
