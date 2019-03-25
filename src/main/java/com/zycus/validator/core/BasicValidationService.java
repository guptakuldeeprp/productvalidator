package com.zycus.validator.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicValidationService implements ValidationService {


    @Override
    public List<String> validate(Stream<String> data, Predicate<String>... validations) {

        Predicate<String> combinedValidation = Arrays.stream(validations).reduce(Predicate::or).orElse(s -> true);
        return data.filter(combinedValidation).collect(Collectors.toList());

    }

    //    @Override
    @Override
    public Map<String, List<String>> validate2(List<String> data, Validation<String>... validations) {

        return Arrays
                .stream(validations)
                .reduce(new HashMap<>(), /* Identity */
                        (accumulator, validation) -> { /* Accumulator */
                            accumulator.put(validation.getName(), data.stream().filter(validation::test).collect(Collectors.toList()));
                            return accumulator;
                        },
                        (acc1, acc2) -> { /* Combiner */
                            acc1.forEach((k, v) -> acc2.merge(k, v, (v1, v2) -> {
                                v1.addAll(v2);
                                return v1;
                            }));
                            return acc1;
                        });

    }

    public static void main(String[] args) {


        Predicate<CharSequence> pp = s -> s.length() != 5;
        Validation<String> p = new Validation<String>(s -> s.trim().isEmpty(), "v1");
        Validation<String> p1 = new Validation<String>(pp, "v2");
        Validation<String> p2 = new Validation<String>(s -> {
            try {
                return Long.valueOf(s) > 88888;
            } catch (Exception e) {
                return false;
            }

        }, "v3");

        Predicate<String> sp = s -> s.trim().isEmpty();
        Predicate<String> sp1 = s -> s.trim().length() != 5;
        Predicate<String> sp2 = s -> Long.valueOf(s) > 88888;

        List<String> l = Arrays.asList(new String[]{"34567", "    ", "12", "99998", "12345"});
//        List<String> l = Arrays.asList(new String[]{"34567"});

        ValidationService vs = new BasicValidationService();
        System.out.println(vs.validate(l.stream(), sp, sp1, sp2));
        System.out.println(vs.validate2(l, p, p1, p2));
    }


}
