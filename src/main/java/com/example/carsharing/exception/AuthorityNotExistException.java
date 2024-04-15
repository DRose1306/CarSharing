package com.example.carsharing.exception;

public class AuthorityNotExistException extends RuntimeException {
    public AuthorityNotExistException(String message) {
        super(message);
    }
}
