package com.deizon.services.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.List;

public class ItemNotFoundException extends RuntimeException implements GraphQLError {

    public ItemNotFoundException(Class<?> clazz) {
        super("Can not find " + clazz.getName() + " instance with specified information.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
