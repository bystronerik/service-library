package com.deizon.services.exception;

public class NotImplementedException extends BaseException {

    public NotImplementedException() {
        super("Function not implemented.");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
