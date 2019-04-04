package com.zycus.validator.tests;

import com.nurkiewicz.lazyseq.LazySeq;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class Demo {

    public static void main(String[] args) {

        IntStream infStream = IntStream.generate(() -> 1);

        PrimitiveIterator.OfInt itr = infStream.iterator();



    }

    private LazySeq<Integer> naturals(int from) {
        return LazySeq.cons(from, () -> naturals(from + 1));
    }
}
