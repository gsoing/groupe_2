package com.episen.tp2.business.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException (String message) {
        super(message);
    }
}
