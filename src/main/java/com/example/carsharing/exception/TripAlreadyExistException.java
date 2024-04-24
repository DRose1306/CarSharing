package com.example.carsharing.exception;

public class TripAlreadyExistException extends RuntimeException {
    public TripAlreadyExistException(String message) {
        super(message);
    }
}
