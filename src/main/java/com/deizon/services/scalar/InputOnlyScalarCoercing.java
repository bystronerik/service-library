package com.deizon.services.scalar;

import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingSerializeException;

public abstract class InputOnlyScalarCoercing<T, I> extends ScalarCoercing<T, I, Void> {

    private final String inputOnlyMessage;
    private final String useVariableMessage;

    public InputOnlyScalarCoercing(Class<T> clazz, Class<I> inputClazz) {
        super(clazz, inputClazz);

        this.inputOnlyMessage = this.clazz.getName() + " is an input-only type";
        this.useVariableMessage =
                "Must use variables to specify " + this.clazz.getSimpleName() + " values.";
    }

    @Override
    protected Void serializeToOutput(T data) {
        throw new CoercingSerializeException(this.inputOnlyMessage);
    }

    @Override
    public T parseLiteral(Object input) {
        throw new CoercingParseLiteralException(this.useVariableMessage);
    }
}
