package com.chrispbacon.chrispbaconend.exception;

public class IllegalInputException extends RuntimeException{
    public IllegalInputException(String message) {
        super(message);
    }
}
