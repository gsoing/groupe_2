package com.episen.tp2.business.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String message) {
        super(message);
    }
}
