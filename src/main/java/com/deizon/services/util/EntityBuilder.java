package com.deizon.services.util;

import com.deizon.services.model.ListChange;
import java.util.List;

public class EntityBuilder<T> {

    private final T entity;

    public EntityBuilder(T entity) {
        this.entity = entity;
    }

    public EntityBuilder<T> listField(
            DataGetter<ListChange> inputGetter, DataGetter<List<String>> storeGetter) {
        if (inputGetter.get() != null) {
            inputGetter.get().process(storeGetter.get());
        }

        return this;
    }

    public <F> EntityBuilder<T> field(DataGetter<F> getter, DataSetter<F> setter) {
        if (getter.get() != null) {
            setter.set(getter.get());
        }

        return this;
    }

    public T getEntity() {
        return this.entity;
    }

    public interface DataGetter<T> {
        T get();
    }

    public interface DataSetter<T> {
        void set(T data);
    }
}
