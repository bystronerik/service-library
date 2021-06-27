package com.deizon.services.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.List;

public class GraphQLAccessDeniedException extends RuntimeException implements GraphQLError {

    public GraphQLAccessDeniedException() {
        super("Access denied");
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
    public ErrorClassification getErrorType() {
        return null;
    }
}
