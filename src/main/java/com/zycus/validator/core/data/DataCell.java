package com.zycus.validator.core.data;

import com.google.common.reflect.TypeToken;
import com.zycus.validator.core.ex.CellValueTypeException;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class DataCell {

    private final Object value;

    public DataCell(Object value) {
        this.value = value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    public Optional<String> asStringOptional() {
        return makeOptionalRaw(String::valueOf, String.class);
    }

    public Boolean asBoolean() {
        if (value instanceof Boolean)
            return (Boolean) value;

        return Boolean.valueOf(asString());
    }

    public Optional<Boolean> asBooleanOptional() {
        return makeOptional(Boolean::valueOf, Boolean.class);
    }

    public Integer asInt() {
        if (value instanceof Integer)
            return (Integer) value;
        return Integer.valueOf(asString());
    }

    public Optional<Integer> asIntOptional() {
        return makeOptional(Integer::valueOf, Integer.class);
    }


    public Long asLong() {
        if (value instanceof Long)
            return (Long) value;
        return Long.valueOf(asString());

    }

    public Optional<Long> asLongOptional() {
        return makeOptional(Long::valueOf, Long.class);
    }

    public Object raw() {
        return value;
    }

    public <R> Optional<R> makeOptional(Function<String, R> mapper, Class<R> clazz) {
        try {
            return Optional.ofNullable(asString()).map(mapper);
        } catch (Exception e) {

            throw new CellValueTypeException(e, clazz);
        }

    }

    private <R> Optional<R> makeOptionalRaw(Function<Object, R> mapper, Class<R> clazz) {
        try {
            return Optional.ofNullable(asString()).map(mapper);
        } catch (Exception e) {

            throw new CellValueTypeException(e, clazz);
        }

    }


    public static void main(String[] args) {

        DataCell cell = new DataCell("123");
        System.out.println(cell.makeOptional(Integer::valueOf, Integer.class));

    }


}
