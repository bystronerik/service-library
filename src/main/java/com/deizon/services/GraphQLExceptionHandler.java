package com.deizon.services;

import com.deizon.services.exception.BaseException;
import com.deizon.services.exception.GraphQLAccessDeniedException;
import graphql.ExceptionWhileDataFetching;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GraphQLExceptionHandler.class);

    @Override
    public DataFetcherExceptionHandlerResult onException(
            DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        SourceLocation sourceLocation = handlerParameters.getSourceLocation();
        ResultPath path = handlerParameters.getPath();

        if (exception instanceof AccessDeniedException) {
            return DataFetcherExceptionHandlerResult.newResult()
                    .error(new GraphQLAccessDeniedException())
                    .build();
        }

        ExceptionWhileDataFetching error =
                new ExceptionWhileDataFetching(path, exception, sourceLocation);

        if (!(exception instanceof BaseException)) {
            log.warn(error.getMessage(), exception);
        }

        return DataFetcherExceptionHandlerResult.newResult().error(error).build();
    }
}
