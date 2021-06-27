package com.deizon.services.service;

import com.deizon.services.model.Entity;

public interface IService<T extends Entity, C extends U, U, F> {

    T find(F input);

    Iterable<T> findAll(F input);

    T create(C data);

    T update(String id, U data);

    T delete(String id);

    boolean totalDelete(String id);
}
