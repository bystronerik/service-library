package com.deizon.services.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BaseRepository<T> extends MongoRepository<T, String> {

    @NotNull
    @Override
    @Query("{'id': {$in: ?0}, 'deleted': false}")
    Iterable<T> findAllById(@NotNull Iterable<String> ids);

    @NotNull
    @Override
    @Query("{'id': ?0, 'deleted': false}")
    Optional<T> findById(@NotNull String id);

    @NotNull
    @Query("{'id': {$in: ?0}, 'clientId': ?1, 'deleted': false}")
    Iterable<T> findAllById(@NotNull Iterable<String> ids, @NotNull String clientId);

    @NotNull
    @Query("{'id': ?0, 'clientId': ?1, 'deleted': false}")
    Optional<T> findById(@NotNull String id, @NotNull String clientId);

    @DeleteQuery("{'id': ?0, 'clientId': ?1}")
    void deleteById(String id, String clientId);

}
