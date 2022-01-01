package com.deizon.services.service;

import com.deizon.services.exception.ItemNotFoundException;
import com.deizon.services.model.Entity;
import com.deizon.services.model.FindInput;
import com.deizon.services.repository.BaseRepository;
import com.deizon.services.util.ExampleBuilder;

public abstract class BaseService<
                T extends Entity, C extends U, U, F extends FindInput, R extends BaseRepository<T>>
        extends InternalService<T, C, U, F, R> implements Service<T, C, U, F> {

    protected BaseService(
            Class<T> entityClass,
            R repository,
            Class<C> createInputClass,
            Class<U> updateInputClass) {
        super(entityClass, repository, createInputClass, updateInputClass);
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
        return super.processExample(builder)
                .field("clientId", () -> clientId, builder.getEntity()::setClientId);
    }

    @Override
    public T create(C data, String clientId) {
        final T entity = createEntity(data);
        preprocessCreate(entity, data, clientId);
        entity.setClientId(clientId);
        return processData(entity, data);
    }

    public void preprocessCreate(T entity, C data, String clientId) {}

    @Override
    public T update(String id, U data, String clientId) {
        final T entity =
                this.repository
                        .findById(id, clientId)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        preprocessUpdate(entity, data, clientId);
        return processData(entity, data);
    }

    public void preprocessUpdate(T entity, U data, String clientId) {}

    @Override
    public T delete(String id, String clientId) {
        final T entity =
                this.repository
                        .findById(id, clientId)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        return delete(entity);
    }

    @Override
    public boolean totalDelete(String id, String clientId) {
        repository.deleteById(id, clientId);
        return true;
    }
}
