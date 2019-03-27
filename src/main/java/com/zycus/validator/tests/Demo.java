package com.zycus.validator.tests;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class Demo {

    public static void main(String[] args) {

        IntStream infStream = IntStream.generate(() -> 1);

        PrimitiveIterator.OfInt itr = infStream.iterator();



    }
}
