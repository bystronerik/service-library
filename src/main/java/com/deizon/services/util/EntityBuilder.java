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

    @Deprecated
    public EntityBuilder<T> stringField(DataGetter<String> getter, DataSetter<String> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> integerField(DataGetter<Integer> getter, DataSetter<Integer> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> doubleField(DataGetter<Double> getter, DataSetter<Double> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> bigDecimalField(
            DataGetter<BigDecimal> getter, DataSetter<BigDecimal> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> booleanField(DataGetter<Boolean> getter, DataSetter<Boolean> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> datetimeField(DataGetter<Instant> getter, DataSetter<Instant> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public EntityBuilder<T> objectField(DataGetter<Object> getter, DataSetter<Object> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public <F extends Enum<?>> EntityBuilder<T> enumField(
            DataGetter<F> getter, DataSetter<F> setter) {
        return this.field(getter, setter);
    }

    @Deprecated
    public <F> EntityBuilder<T> listField(DataGetter<List<F>> getter, DataSetter<List<F>> setter) {
        return this.field(getter, setter);
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
