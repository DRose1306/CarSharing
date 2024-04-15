package com.example.carsharing.exception;

public class RoleNotExistException extends RuntimeException {
    public RoleNotExistException(String message) {
        super(message);
    }
}
