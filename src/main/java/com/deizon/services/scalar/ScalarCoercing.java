package com.deizon.services.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

public abstract class ScalarCoercing<T, I, O> implements Coercing<T, O> {

    protected final Class<T> clazz;
    protected final Class<I> inputClazz;

    private final String invalidTypeMessage;
    private final String invalidInputMessage;

    public ScalarCoercing(Class<T> clazz, Class<I> inputClazz) {
        this.clazz = clazz;
        this.inputClazz = inputClazz;

        this.invalidTypeMessage = "Expected a " + this.clazz.getSimpleName() + " object.";
        this.invalidInputMessage = "Not a valid " + this.clazz.getSimpleName() + " value: '%s'.";
    }

    @Override
    public O serialize(final Object dataFetcherResult) {
        if (this.clazz.isInstance(dataFetcherResult)) {
            return this.serializeToOutput(this.clazz.cast(dataFetcherResult));
        } else {
            throw new CoercingSerializeException(this.invalidTypeMessage);
        }
    }

    @Override
    public T parseValue(final Object input) {
        if (this.inputClazz.isInstance(input)) {
            try {
                return this.parseFromInput(this.inputClazz.cast(input));
            } catch (Exception e) {
                throw new CoercingParseValueException(
                        String.format(this.invalidInputMessage, input), e);
            }
        } else {
            throw new CoercingParseValueException(String.format(this.invalidInputMessage, input));
        }
    }

    @Override
    public T parseLiteral(final Object input) {
        if (input instanceof StringValue) {
            try {
                return this.parseFromInput(this.inputClazz.cast(((StringValue) input).getValue()));
            } catch (Exception e) {
                throw new CoercingParseValueException(
                        String.format(this.invalidInputMessage, input), e);
            }
        } else {
            throw new CoercingParseLiteralException("Expected a StringValue object.");
        }
    }

    protected abstract O serializeToOutput(final T data);

    protected abstract T parseFromInput(I input);
}
