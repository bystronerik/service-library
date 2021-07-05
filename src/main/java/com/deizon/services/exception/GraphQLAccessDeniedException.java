package com.deizon.services.exception;

public class GraphQLAccessDeniedException extends BaseException {

    public GraphQLAccessDeniedException() {
        super("Access denied");
    }
}
