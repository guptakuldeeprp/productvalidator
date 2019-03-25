package com.zycus.validator.core;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ValidationService {
    //    @Override
    List<String> validate(Stream<String> data, Predicate<String>... validations);

    //    @Override
    Map<String, List<String>> validate2(List<String> data, Validation<String>... validations);
}
