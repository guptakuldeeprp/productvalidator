package com.zycus.validator.core.data;

import com.zycus.validator.core.Validation;

import java.util.List;

public class ValidationDescription {

    /**
     * validations starts from position 1
     */
    private List<List<Validation>> validations;

    public ValidationDescription(List<List<Validation>> validations) {
        this.validations = validations;
    }

    /**
     * column position starts from one
     * @param columnPos
     * @return
     */
    public List<Validation> getValidations(int columnPos) {
        return validations.get(columnPos);
    }



}
