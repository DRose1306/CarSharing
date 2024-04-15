package com.example.carsharing.exception;

public class PaymentNotExistException extends RuntimeException {
    public PaymentNotExistException(String message) {
        super(message);
    }
}
