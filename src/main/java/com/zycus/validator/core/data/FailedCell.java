package com.zycus.validator.core.data;

import com.zycus.validator.core.Validation;

import java.util.List;

public class FailedCell {

    private int i, j; // positions in source
    private DataCell failed;
    private List<Validation> failedValidation;

    public FailedCell(DataCell failed, List<Validation> failedValidation) {
        this.failed = failed;
        this.failedValidation = failedValidation;
    }

    public FailedCell(DataCell failed, List<Validation> failedValidation, int i, int j) {
        this.i = i;
        this.j = j;
        this.failed = failed;
        this.failedValidation = failedValidation;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public FailedCell setPos(int i, int j) {
        this.i = i;
        this.j = j;
        return this;
    }

    public DataCell getFailed() {
        return failed;
    }

    public List<Validation> getFailedValidation() {
        return failedValidation;
    }
}
