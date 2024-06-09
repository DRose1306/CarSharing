package com.example.carsharing.controller.handler;

import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * Represents additional error information to be included in error responses.
 */
@Value
public class ErrorExtension {
    /**
     * The error message.
     */
    String message;

    /**
     * The HTTP status code associated with the error.
     */
    HttpStatus errorCode;

    /**
     * Constructs a new ErrorExtension object with the given message and HTTP status code.
     *
     * @param message   the error message
     * @param errorCode the HTTP status code
     */
    public ErrorExtension(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
