package com.deizon.services.service;

import com.deizon.services.model.Entity;

public interface Service<T extends Entity, C extends U, U, F> {

    T find(F input, String clientId);

    Iterable<T> findAll(F input, String clientId);

    T create(C data, String clientId);

    T update(String id, U data, String clientId);

    T delete(String id, String clientId);

    boolean totalDelete(String id, String clientId);
}
