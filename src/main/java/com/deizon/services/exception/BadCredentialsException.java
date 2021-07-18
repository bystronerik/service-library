package com.deizon.services.exception;

public class BadCredentialsException extends BaseException {

    public BadCredentialsException() {
        super("Bad username or password!");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
