package com.zycus.validator.core.data;

import com.zycus.validator.core.ex.CellValueTypeException;

import java.util.Optional;
import java.util.function.Function;

public class DataCell {

    private Object value;

    public DataCell(Object value) {
        this.value = value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    public Optional<String> asStringOptional() {
        return makeOptionalRaw(String::valueOf);
    }

    public Boolean asBoolean() {
        if (value instanceof Boolean)
            return (Boolean) value;

        return Boolean.valueOf(asString());
    }

    public Optional<Boolean> asBooleanOptional() {
        return makeOptional(Boolean::valueOf);
    }

    public Integer asInt() {
        if (value instanceof Integer)
            return (Integer) value;
        return Integer.valueOf(asString());
    }

    public Optional<Integer> asIntOptional() {
        return makeOptional(Integer::valueOf);
    }


    public Long asLong() {
        if (value instanceof Long)
            return (Long) value;
        return Long.valueOf(asString());

    }

    public Optional<Long> asLongOptional() {
        return makeOptional(Long::valueOf);
    }

    public Object raw() {
        return value;
    }

    private <R> Optional<R> makeOptional(Function<String, R> mapper) {
        R t = null;
        try {
            return Optional.ofNullable(asString()).map(mapper);
        } catch (Exception e) {
            throw new CellValueTypeException(e, t.getClass());
        }

    }

    private <T, R> Optional<R> makeOptionalRaw(Function<Object, R> mapper) {
        R t = null;
        try {
            return Optional.ofNullable(asString()).map(mapper);
        } catch (Exception e) {
            throw new CellValueTypeException(e, t.getClass());
        }

    }


    public static void main(String[] args) {
    }


}
