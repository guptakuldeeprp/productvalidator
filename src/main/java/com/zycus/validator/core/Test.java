package com.zycus.validator.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Test {

    static Map<String, Function<String, Object>> functionMap;

    static {
        functionMap = new HashMap<>();
        functionMap.put("len", s -> (long) s.length());
    }

    public static void main(String[] args) {
//        System.out.println(exec("len", "wiieyfwyyrbe4"));;



    }

    public static Object exec(String op, String arg) {
        return functionMap.get(op).apply(arg);
    }
}
