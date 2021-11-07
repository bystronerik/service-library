package com.deizon.services.util;

import com.mongodb.BasicDBList;
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

    public <F> ExampleBuilder<T> listField(
            String name, DataGetter<List<F>> getter, DataSetter<List<F>> setter) {
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
