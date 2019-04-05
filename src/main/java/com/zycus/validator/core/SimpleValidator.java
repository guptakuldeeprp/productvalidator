package com.zycus.validator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimpleValidator {

    public static boolean isNull(Object o) {
        return Objects.isNull(o);
    }

    public static Predicate<Long> getLenValidator(final long limit) {
        return l -> l > limit;

    }

    public static boolean  lengthLessThan(long len, long limit) {
        return len > limit;
    }

    public static void main(String[] args) {

        List<Long> longs = new ArrayList<Long>(){
            {
                add(45L);
                add(25L);
                add(35L);
                add(65L);
                add(75L);
                add(415L);
                add(45L);
                add(45L);
                add(45L);
                add(55L);
                add(65L);
                add(95L);
            }
        };

        Predicate<Long> pred = s -> s.compareTo(50L) > 0;
        System.out.println(longs.stream().filter(getLenValidator(50)).collect(Collectors.toList()));


    }


}
