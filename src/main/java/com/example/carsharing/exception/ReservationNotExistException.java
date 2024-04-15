package com.example.carsharing.exception;

public class ReservationNotExistException extends RuntimeException {
    public ReservationNotExistException(String message) {
        super(message);
    }
}
