package com.deizon.services.repository;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BaseGlobalRepository<T> extends MongoRepository<T, String> {

    @NotNull
    @Override
    @Query("{'deleted': false}")
    List<T> findAll();

    @NotNull
    @Override
    @Query("{'id': {$in: ?0}, 'deleted': false}")
    Iterable<T> findAllById(@NotNull Iterable<String> ids);

    @NotNull
    @Override
    @Query("{'id': ?0, 'deleted': false}")
    Optional<T> findById(@NotNull String id);
}
