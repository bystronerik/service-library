package com.deizon.services.scalar;

public abstract class StringInputScalarCoercing<T> extends ScalarCoercing<T, String, String> {

    public StringInputScalarCoercing(Class<T> clazz) {
        super(clazz, String.class);
    }
}
