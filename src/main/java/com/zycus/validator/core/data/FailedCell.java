package com.zycus.validator.core.data;

import java.util.Optional;

import static com.zycus.validator.core.data.ValidationDescription.ValidationGroup;

public class FailedCell implements Cell {

    private int i, j; // positions in source
    private Cell failed;
    private ValidationGroup failedValidations;

    public FailedCell(Cell failed, ValidationGroup failedValidation) {
        this.failed = failed;
        this.failedValidations = failedValidation;
    }

    public FailedCell(Cell failed, ValidationGroup failedValidation, int i, int j) {
        this.i = i;
        this.j = j;
        this.failed = failed;
        this.failedValidations = failedValidation;
    }

    public FailedCell setPos(int i, int j) {
        this.i = i;
        this.j = j;
        return this;
    }

    public FailedCell setPos(int[] pos) {
        this.i = pos[0];
        this.j = pos[1];
        return this;
    }


    @Override
    public String asString() {
        return failed.asString();
    }

    @Override
    public Optional<String> asStringOptional() {
        return failed.asStringOptional();
    }

    @Override
    public Boolean asBoolean() {
        return failed.asBoolean();
    }

    @Override
    public Optional<Boolean> asBooleanOptional() {
        return failed.asBooleanOptional();
    }

    @Override
    public Integer asInt() {
        return failed.asInt();
    }

    @Override
    public Optional<Integer> asIntOptional() {
        return failed.asIntOptional();
    }

    @Override
    public Long asLong() {
        return failed.asLong();
    }

    @Override
    public Optional<Long> asLongOptional() {
        return failed.asLongOptional();
    }

    @Override
    public Object raw() {
        return failed.raw();
    }

    public int[] getPos() {
        return new int[]{i, j};
    }

    public Cell getFailed() {
        return failed;
    }

    public ValidationGroup getFailedValidations() {
        return failedValidations;
    }
}
