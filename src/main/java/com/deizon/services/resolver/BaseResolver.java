package com.deizon.services.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseResolver<T> implements GraphQLResolver<T> {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    protected <F> CompletableFuture<F> processAsync(AsyncCallable<F> callable) {
        return CompletableFuture.supplyAsync(callable::run, executor);
    }

    protected interface AsyncCallable<T> {
        T run();
    }
}
