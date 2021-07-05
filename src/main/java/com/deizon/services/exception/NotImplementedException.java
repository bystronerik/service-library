package com.deizon.services.exception;

import graphql.ErrorType;
import graphql.language.SourceLocation;
import java.util.List;

public class NotImplementedException extends BaseException {

    public NotImplementedException() {
        super("Function not implemented.");
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
