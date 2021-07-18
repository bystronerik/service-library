package com.deizon.services.service;

import com.deizon.services.exception.ItemNotFoundException;
import com.deizon.services.exception.NotImplementedException;
import com.deizon.services.model.Entity;
import com.deizon.services.model.FindInput;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class BaseService<
                T extends Entity,
                C extends U,
                U,
                F extends FindInput,
                R extends MongoRepository<T, String>>
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
    public T find(F input) {
        return repository
                .findOne(createExample(input))
                .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
    }

    @Override
    public Iterable<T> findAll(F input) {
        return repository.findAll(createExample(input));
    }

    @Override
    public T create(C data) {
        return processData(createEntity(data), data);
    }

    @Override
    public T update(String id, U data) {
        return processData(
                this.repository
                        .findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass)),
                data);
    }

    @Override
    public T delete(String id) {
        final T entity =
                this.repository
                        .findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(this.entityClass));
        entity.setDeleted(true);
        entity.setDeleteDate(Instant.now());

        return this.repository.save(entity);
    }

    @Override
    public boolean totalDelete(String id) {
        repository.deleteById(id);
        return true;
    }

    protected abstract Example<T> createExample(F input);

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
