package com.zycus.validator.core.data;

import com.zycus.validator.core.ex.CellValueTypeException;

import java.util.Optional;
import java.util.function.Function;

public interface Cell {

    String asString();

    Optional<String> asStringOptional();

    Boolean asBoolean();

    Optional<Boolean> asBooleanOptional();

    Integer asInt();

    Optional<Integer> asIntOptional();

    Long asLong();

    Optional<Long> asLongOptional();

    Object raw();

    int[] getPos();


    default <R> R mapRaw(Class<R> clazz) {
        if (Long.class.isAssignableFrom(clazz))
            return (R) asLong();
        if (Boolean.class.isAssignableFrom(clazz))
            return (R) asBoolean();
        if (Integer.class.isAssignableFrom(clazz))
            return (R) asInt();
        if (String.class.isAssignableFrom(clazz))
            return (R) asString();
        throw new CellValueTypeException("Cannot map from " + raw().getClass() + " to " + clazz, clazz);

    }

    default <R> R mapRaw(Function<Object, R> mapper, Class<R> clazz) {
        try {
            return mapper.apply(raw());
        } catch (Exception e) {
            throw new CellValueTypeException(e, clazz);
        }
    }

    default <R> Optional<R> mapRawOptional(Function<Object, R> mapper, Class<R> clazz) {
        try {
            return Optional.ofNullable(raw()).map(mapper);
        } catch (Exception e) {
            throw new CellValueTypeException(e, clazz);
        }
    }
}
