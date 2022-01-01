package com.deizon.services.repository;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;

public interface BaseRepository<T> extends BaseGlobalRepository<T> {

    @NotNull
    @Query("{'clientId': ?0, 'deleted': false}")
    Iterable<T> findAll(@NotNull String clientId);

    @NotNull
    @Query("{'id': {$in: ?0}, 'clientId': ?1, 'deleted': false}")
    Iterable<T> findAllById(@NotNull Iterable<String> ids, @NotNull String clientId);

    @NotNull
    @Query("{'id': ?0, 'clientId': ?1, 'deleted': false}")
    Optional<T> findById(@NotNull String id, @NotNull String clientId);

    @DeleteQuery("{'id': ?0, 'clientId': ?1}")
    void deleteById(String id, String clientId);
}
