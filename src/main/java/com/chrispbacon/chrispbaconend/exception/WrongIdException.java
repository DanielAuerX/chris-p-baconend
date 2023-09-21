package com.chrispbacon.chrispbaconend.exception;

public class WrongIdException extends RuntimeException{
    public WrongIdException(String message) {
        super(message);
    }
}
