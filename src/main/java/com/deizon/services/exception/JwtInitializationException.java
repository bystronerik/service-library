package com.deizon.services.exception;

public class JwtInitializationException extends RuntimeException {
    public JwtInitializationException(Throwable e) {
        super("Something went wong while reading private/public key!", e);
    }
}
