package com.deizon.services.exception;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException(Class<?> clazz) {
        super("Can not find " + clazz.getSimpleName() + " instance with specified information.");
    }
}
