package com.example.carsharing.exception;

public class CarAlreadyExistException extends RuntimeException {
    public CarAlreadyExistException(String message) {
        super(message);
    }
}