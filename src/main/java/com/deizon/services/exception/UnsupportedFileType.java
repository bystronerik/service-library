package com.deizon.services.exception;

public class UnsupportedFileType extends BaseException {

    public UnsupportedFileType() {
        super("Provided unsupported file type!");
    }

    public UnsupportedFileType(String message) {
        super(message);
    }
}
