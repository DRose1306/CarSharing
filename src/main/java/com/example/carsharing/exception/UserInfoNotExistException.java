package com.example.carsharing.exception;

public class UserInfoNotExistException extends RuntimeException {
    public UserInfoNotExistException(String message) {
        super(message);
    }
}
