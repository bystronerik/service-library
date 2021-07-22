package com.deizon.services.util;

import com.deizon.services.model.ListChange;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class EntityBuilder<T> {

    private final T entity;

    public EntityBuilder(T entity) {
        this.entity = entity;
    }

    public EntityBuilder<T> stringField(DataGetter<String> getter, DataSetter<String> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> integerField(DataGetter<Integer> getter, DataSetter<Integer> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> doubleField(DataGetter<Double> getter, DataSetter<Double> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> bigDecimalField(
            DataGetter<BigDecimal> getter, DataSetter<BigDecimal> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> booleanField(DataGetter<Boolean> getter, DataSetter<Boolean> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> datetimeField(DataGetter<Instant> getter, DataSetter<Instant> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> objectField(DataGetter<Object> getter, DataSetter<Object> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> enumField(
            DataGetter<? extends Enum<?>> getter, DataSetter<? extends Enum<?>> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> listField(DataGetter<List<?>> getter, DataSetter<List<?>> setter) {
        return this.process(getter, setter);
    }

    public EntityBuilder<T> listField(
            DataGetter<ListChange> inputGetter, DataGetter<List<String>> storeGetter) {
        if (inputGetter.get() != null) {
            inputGetter.get().process(storeGetter.get());
        }

        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private EntityBuilder<T> process(DataGetter getter, DataSetter setter) {
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
