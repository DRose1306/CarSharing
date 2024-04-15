package com.example.carsharing.exception;

public class TripNotExistException extends RuntimeException {
    public TripNotExistException(String message) {
        super(message);
    }
}
