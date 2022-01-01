package com.deizon.services.service;

import com.deizon.services.exception.ItemNotFoundException;
import com.deizon.services.model.Entity;
import com.deizon.services.model.FindInput;
import com.deizon.services.repository.BaseRepository;

public abstract class BaseGlobalService<
                T extends Entity, C extends U, U, F extends FindInput, R extends BaseRepository<T>>
        extends InternalService<T, C, U, F, R> implements GlobalService<T, C, U, F> {

    protected BaseGlobalService(
            Class<T> entityClass,
            R repository,
            Class<C> createInputClass,
            Class<U> updateInputClass) {
        super(entityClass, repository, createInputClass, updateInputClass);
    }

    @Override
    public T find(F input) {
        return repository
                .findOne(processExample(createExample(input)).create())
                .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
    }

    @Override
    public Iterable<T> findAll(F input) {
        return repository.findAll(processExample(createExample(input)).create());
    }

    @Override
    public T create(C data) {
        final T entity = createEntity(data);
        preprocessCreate(entity, data);
        return processData(entity, data);
    }

    public void preprocessCreate(T entity, C data) {}

    @Override
    public T update(String id, U data) {
        final T entity =
                this.repository
                        .findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        preprocessUpdate(entity, data);
        return processData(entity, data);
    }

    public void preprocessUpdate(T entity, U data) {}

    @Override
    public T delete(String id) {
        final T entity =
                this.repository
                        .findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        return delete(entity);
    }

    @Override
    public boolean totalDelete(String id) {
        repository.deleteById(id);
        return true;
    }
}
