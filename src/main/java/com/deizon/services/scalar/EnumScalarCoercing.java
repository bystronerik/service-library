package com.deizon.services.scalar;

public abstract class EnumScalarCoercing<T extends Enum<T>> extends StringInputScalarCoercing<T> {

    public EnumScalarCoercing(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected String serializeToOutput(T data) {
        return data.name();
    }

    @Override
    protected T parseFromInput(String input) {
        return T.valueOf(this.clazz, input);
    }
}
