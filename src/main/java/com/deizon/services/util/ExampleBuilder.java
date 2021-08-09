package com.deizon.services.util;

import com.mongodb.BasicDBList;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;

public class ExampleBuilder<T> {

    private final T entity;
    private ExampleMatcher matcher;
    private GenericPropertyMatcher propertyMatcher;

    public ExampleBuilder(T entity) {
        this.entity = entity;
        this.matcher = ExampleMatcher.matching();
    }

    public ExampleBuilder<T> all() {
        this.matcher = ExampleMatcher.matchingAll();
        return this;
    }

    public ExampleBuilder<T> any() {
        this.matcher = ExampleMatcher.matchingAny();
        return this;
    }

    // TODO dodělat i další property matchery, ale momentálně nejsou potřeba
    public ExampleBuilder<T> exact() {
        this.propertyMatcher = new GenericPropertyMatcher().exact();
        return this;
    }

    @Deprecated
    public ExampleBuilder<T> stringField(
            String name, DataGetter<String> getter, DataSetter<String> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> integerField(
            String name, DataGetter<Integer> getter, DataSetter<Integer> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> doubleField(
            String name, DataGetter<Double> getter, DataSetter<Double> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> bigDecimalField(
            String name, DataGetter<BigDecimal> getter, DataSetter<BigDecimal> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> booleanField(
            String name, DataGetter<Boolean> getter, DataSetter<Boolean> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> datetimeField(
            String name, DataGetter<Instant> getter, DataSetter<Instant> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public ExampleBuilder<T> objectField(
            String name, DataGetter<Object> getter, DataSetter<Object> setter) {
        return this.field(name, getter, setter);
    }

    @Deprecated
    public <F extends Enum<?>> ExampleBuilder<T> enumField(
            String name, DataGetter<F> getter, DataSetter<F> setter) {
        return this.field(name, getter, setter);
    }

    public ExampleBuilder<T> listField(
            String name, DataGetter<List<?>> getter, DataSetter<List<?>> setter) {
        if (getter.get() != null) {
            setter.set(getter.get());
            this.matcher =
                    this.matcher.withMatcher(
                            name,
                            match ->
                                    match.transform(
                                                    source ->
                                                            Optional.of(
                                                                    ((BasicDBList) source.get())
                                                                            .iterator()
                                                                            .next()))
                                            .exact());
        }

        return this;
    }

    public <F> ExampleBuilder<T> field(String name, DataGetter<F> getter, DataSetter<F> setter) {
        if (getter.get() != null) {
            if (getter.get().equals("NULL")) {
                setter.set(null);
            } else {
                setter.set(getter.get());
            }

            this.matcher = this.matcher.withMatcher(name, this.propertyMatcher);
        }

        return this;
    }

    public Example<T> create() {
        return Example.of(this.entity, this.matcher);
    }

    public interface DataGetter<T> {
        T get();
    }

    public interface DataSetter<T> {
        void set(T data);
    }
}
