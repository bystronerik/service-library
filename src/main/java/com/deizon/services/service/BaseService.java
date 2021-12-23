package com.deizon.services.service;

import com.deizon.services.exception.ItemNotFoundException;
import com.deizon.services.exception.NotImplementedException;
import com.deizon.services.model.Entity;
import com.deizon.services.model.FindInput;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

import com.deizon.services.repository.BaseRepository;
import com.deizon.services.util.ExampleBuilder;

public abstract class BaseService<
                T extends Entity,
                C extends U,
                U,
                F extends FindInput,
                R extends BaseRepository<T>>
        implements Service<T, C, U, F> {

    private final Class<C> createInputClass;
    private final Class<U> updateInputClass;

    protected final Class<T> entityClass;
    protected final R repository;

    protected BaseService(
            Class<T> entityClass,
            R repository,
            Class<C> createInputClass,
            Class<U> updateInputClass) {
        this.entityClass = entityClass;
        this.repository = repository;
        this.createInputClass = createInputClass;
        this.updateInputClass = updateInputClass;
    }

    @Override
    public T find(F input, String clientId) {
        return repository
                .findOne(processExample(createExample(input), clientId).create())
                .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
    }

    @Override
    public Iterable<T> findAll(F input, String clientId) {
        return repository.findAll(processExample(createExample(input), clientId).create());
    }

    private ExampleBuilder<T> processExample(ExampleBuilder<T> builder, String clientId) {
        builder.field("clientId", () -> clientId, builder.getEntity()::setClientId);
        builder.field("deleted", () -> false, builder.getEntity()::setDeleted);
        return builder;
    }

    @Override
    public T create(C data, String clientId) {
        final T entity = createEntity(data);
        preprocessCreate(entity, data, clientId);
        entity.setClientId(clientId);
        return processData(entity, data);
    }

    public void preprocessCreate(T entity, C data, String clientId) {

    }

    @Override
    public T update(String id, U data, String clientId) {
        final T entity = this.repository
                .findById(id, clientId)
                .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        preprocessUpdate(entity, data, clientId);
        return processData(entity, data);
    }

    public void preprocessUpdate(T entity, U data, String clientId) {

    }

    @Override
    public T delete(String id, String clientId) {
        final T entity =
                this.repository
                        .findById(id, clientId)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        entity.setDeleted(true);
        entity.setDeleteDate(Instant.now());

        return this.repository.save(entity);
    }

    @Override
    public boolean totalDelete(String id, String clientId) {
        repository.deleteById(id, clientId);
        return true;
    }

    protected abstract ExampleBuilder<T> createExample(F input);

    protected T createEntity(C data) {
        try {
            return this.entityClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException exception) {
            throw new NotImplementedException();
        }
    }

    protected T processData(T entity, U data) {
        if (this.createInputClass.isInstance(data)) {
            entity.setCreateDate(Instant.now());
            entity.setDeleted(false);
        }

        if (this.updateInputClass.isInstance(data)) {
            entity.setUpdateDate(Instant.now());
        }

        return repository.save(entity);
    }
}
