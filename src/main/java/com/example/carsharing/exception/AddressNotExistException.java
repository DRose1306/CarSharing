package com.example.carsharing.exception;

public class AddressNotExistException extends RuntimeException {
    public AddressNotExistException(String message) {
        super(message);
    }
}
