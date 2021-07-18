package com.deizon.services.exception;

public class BadTokenException extends BaseException {

    public BadTokenException() {
        super("Supplied authentication token is invalid or expired");
    }

    public BadTokenException(String message) {
        super(message);
    }
}
