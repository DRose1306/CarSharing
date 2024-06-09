package com.example.carsharing.controller.handler;

import com.example.carsharing.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for handling specific exceptions and generating appropriate error responses.
 */
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles CarNotExistException and returns an HTTP 404 response.
     */
    @ExceptionHandler(CarNotExistException.class)
    public ResponseEntity<ErrorExtension> handleCarNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles CarAlreadyExistException and returns an HTTP 400 response.
     */
    @ExceptionHandler(CarAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleCarAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles PaymentNotExistException and returns an HTTP 404 response.
     */
    @ExceptionHandler(PaymentNotExistException.class)
    public ResponseEntity<ErrorExtension> handlePaymentNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles PaymentAlreadyExistsException and returns an HTTP 400 response.
     */
    @ExceptionHandler(PaymentAlreadyExistsException.class)
    public ResponseEntity<ErrorExtension> handlePaymentAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles TripNotExistException and returns an HTTP 404 response.
     */
    @ExceptionHandler(TripNotExistException.class)
    public ResponseEntity<ErrorExtension> handleTripNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles TripAlreadyExistException and returns an HTTP 400 response.
     */
    @ExceptionHandler(TripAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleTripAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UserNotExistException and returns an HTTP 404 response.
     */
    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorExtension> handleUserNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserAlreadyExistException and returns an HTTP 400 response.
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleUserAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
}
