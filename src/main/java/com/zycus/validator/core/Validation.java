package com.zycus.validator.core;


import java.util.Objects;
import java.util.function.Predicate;

public class Validation<T> {

    public boolean test(T data) {
        return pred.test(data);
    }

    private String name;
    private String description;
    private Predicate<? super T> pred;

    public Validation(Predicate<? super T> pred) {
        this(pred, null);
    }


    public Validation(Predicate<? super T> pred, String name) {
        this(pred, name, null);
    }

    public Validation(Predicate<? super T> pred, String name, String description) {
        this.pred = pred;
        this.name = Objects.isNull(name) ? getClass().getName() : name;
        this.description = Objects.isNull(description) ? "" : description;
    }

    public Validation<T> and(Validation<? super T> other) {
        Objects.requireNonNull(other);
        return new Validation<>((t) -> test(t) && other.test(t), getName() + " & " + other.getName());
    }

    public Validation<T> or(Validation<? super T> other) {
        Objects.requireNonNull(other);
        return new Validation<>((t) -> test(t) || other.test(t), getName() + " | " + other.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
