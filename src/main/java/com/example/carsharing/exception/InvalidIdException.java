package com.example.carsharing.exception;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String s) {
        super(s);
    }
}
