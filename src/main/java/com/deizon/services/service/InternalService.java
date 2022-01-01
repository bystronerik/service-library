package com.deizon.services.service;

import com.deizon.services.exception.NotImplementedException;
import com.deizon.services.model.Entity;
import com.deizon.services.model.FindInput;
import com.deizon.services.repository.BaseRepository;
import com.deizon.services.util.ExampleBuilder;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

abstract class InternalService<
        T extends Entity, C extends U, U, F extends FindInput, R extends BaseRepository<T>> {

    protected final Class<C> createInputClass;
    protected final Class<U> updateInputClass;

    protected final Class<T> entityClass;
    protected final R repository;

    protected InternalService(
            Class<T> entityClass,
            R repository,
            Class<C> createInputClass,
            Class<U> updateInputClass) {
        this.entityClass = entityClass;
        this.repository = repository;
        this.createInputClass = createInputClass;
        this.updateInputClass = updateInputClass;
    }

    protected ExampleBuilder<T> processExample(ExampleBuilder<T> builder) {
        return builder.field("deleted", () -> false, builder.getEntity()::setDeleted);
    }

    public T delete(T entity) {
        entity.setDeleted(true);
        entity.setDeleteDate(Instant.now());

        return this.repository.save(entity);
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
