package com.zycus.validator.core.data;

import com.zycus.validator.core.Validation;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidationDescription {

    /**
     * validations starts from position 1
     */
    private List<ValidationGroup> validations;

    public ValidationDescription(List<ValidationGroup> validations) {
        this.validations = validations;
    }

    /**
     * column position starts from one
     *
     * @param columnPos
     * @return
     */
    public ValidationGroup getValidations(int columnPos) {
        return validations.get(columnPos);
    }


    public static class ValidationGroup {

        private Set<Validation> validations;
        private Class<?> type;

        public <E> ValidationGroup(Class<E> type) {
            Objects.requireNonNull(type);
            this.type = type;
            validations = new HashSet<>();
        }


        public <E> ValidationGroup(Class<E> type, Validation<E>... validations) {
            Objects.requireNonNull(type);
            Objects.requireNonNull(validations);
            this.type = type;
            this.validations = new HashSet<>(Arrays.asList(validations));
        }

        ValidationGroup(Class type, Set<Validation> validations) {
            this.type = type;
            this.validations = validations;
        }

        public <E> ValidationGroup addValidation(Validation<E> validation) {
            if (!type.isAssignableFrom(validation.getType().getClass()))
                throw new IllegalArgumentException("Validation of type " + validation.getType() + " cannot be assigned to" +
                        "ValidationGroup of type " + type);
            validations.add(validation);
            return this;
        }

        public boolean test(Cell cell) {
            for (Validation validation : validations) {
                if (validation.test(cell.mapRaw(type)))
                    return false;
            }
            return true;
        }

        public Collection<Validation> getValidations() {
            return Collections.unmodifiableCollection(validations);
        }


        public ValidationGroup getFailedValidations(Cell cell) {
            return new ValidationGroup(type, validations.stream().filter(v -> v.test(cell.mapRaw(type))).collect(Collectors.toSet()));
        }

        public ValidationGroup getPassingValidations(Cell cell) {

            Set<Validation> copy = new HashSet<>(getValidations());
            copy.removeAll(getFailedValidations(cell).getValidations());
            return new ValidationGroup(type, copy);
        }

        @Override
        public String toString() {
            return "ValidationGroup{" +
                    "validations=" + validations +
                    '}';
        }
    }

    public static void main(String[] args) {
        Predicate<CharSequence> pp = s -> s.length() != 5;
        Validation<String> p = new Validation<String>(new Validation.TPred<String>(s -> s.trim().isEmpty()) {
        }, "v1");
        Validation<String> p1 = new Validation<String>(new Validation.TPred<String>(pp) {
        }, "v2");
        Validation<String> p2 = new Validation<String>(new Validation.TPred<String>(s -> {
            try {
                return Long.valueOf(s) > 88888;
            } catch (Exception e) {
                return false;
            }

        }) {
        }, "v3");

        ValidationGroup vg = new ValidationGroup(String.class, p, p1, p2);
        Cell cell = new DataCell("88889");
        System.out.println(vg.getPassingValidations(cell));
    }

}
