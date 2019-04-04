package com.zycus.validator.core.data;

import com.zycus.validator.core.Validation;

import java.util.Optional;
import java.util.function.Function;

public class DataCell implements Cell {

    private final Object value;

    private final int i, j;

    public DataCell(Object value) {
        this(value, -1);
    }

    public DataCell(Object value, int j) {
        this(value, -1, j);
    }

    public DataCell(Object value, int i, int j) {
        this.value = value;
        this.i = i;
        this.j = j;
    }

    @Override
    public String asString() {
        return asString(value);
    }


    private String asString(Object value) {
        if (value instanceof String) return (String) value;
        return String.valueOf(value);
    }

    @Override
    public Optional<String> asStringOptional() {
        return mapRawOptional(String::valueOf, String.class);
    }

    @Override
    public Boolean asBoolean() {
        if (value instanceof Boolean)
            return (Boolean) value;

        return Boolean.valueOf(asString());
    }

    @Override
    public Optional<Boolean> asBooleanOptional() {
        return makeOptional(Boolean::valueOf, Boolean.class);
    }

    @Override
    public Integer asInt() {
        if (value instanceof Integer)
            return (Integer) value;
        return Integer.valueOf(asString());
    }

    @Override
    public Optional<Integer> asIntOptional() {
        return makeOptional(Integer::valueOf, Integer.class);
    }


    @Override
    public Long asLong() {
        if (value instanceof Long)
            return (Long) value;
        return Long.valueOf(asString());

    }

    @Override
    public Optional<Long> asLongOptional() {
        return makeOptional(Long::valueOf, Long.class);
    }

//    public <T> T asType(Class<T> clazz) {
//
//    }


    @Override
    public Object raw() {
        return value;
    }

    <R> Optional<R> makeOptional(Function<String, R> mapper, Class<R> clazz) {
        Function<Object, String> f = v -> asString(v);
        return mapRawOptional(f.andThen(mapper), clazz);
    }

    @Override
    public int[] getPos() {
        return new int[]{i, j};
    }

    public static void main(String[] args) {

        DataCell cell = new DataCell("123");
        System.out.println(cell.makeOptional(Integer::valueOf, Integer.class));

    }


}
