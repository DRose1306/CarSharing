package com.example.carsharing.controller.handler;

import com.example.carsharing.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarNotExistException.class)
    public ResponseEntity<ErrorExtension> handleCarNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CarAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleCarAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PaymentNotExistException.class)
    public ResponseEntity<ErrorExtension> handlePaymentNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PaymentAlreadyExistsException.class)
    public ResponseEntity<ErrorExtension> handlePaymentAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(TripNotExistException.class)
    public ResponseEntity<ErrorExtension> handleTripNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TripAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleTripAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorExtension> handleUserNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleUserAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @Description(value = "Отлавливание невалидного UUID с помощью ConstraintViolationException.class")
    @ExceptionHandler(value = { ConstraintViolationException.class, InvalidIdException.class })
    protected ResponseEntity<Object> handleInvalidIdException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        if (ex instanceof ConstraintViolationException) {
            errorMessage = ((ConstraintViolationException) ex).getMessage();
        }
        ErrorExtension errorExtension = new ErrorExtension(errorMessage, errorCode);
        return new ResponseEntity<>(errorExtension, errorCode);
    }
}
