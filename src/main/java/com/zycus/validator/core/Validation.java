package com.zycus.validator.core;


import com.google.common.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Predicate;

public class Validation<T> {

    private String name;

//    public Class<?> type;

    public Type type;

    private String description;

    private Predicate<? super T> pred;

    public static abstract class TPred<T> {
        Predicate<? super T> pred;
        private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
        };
        private final Type type = typeToken.getType(); // or getRawType() to return Class<? super T>

        public TPred(Predicate<? super T> pred) {
            this.pred = pred;
        }

    }

    private final TypeToken typeToken = TypeToken.of(getClass());


//    private final Type type = typeToken.getType();

//    abstract class MyType<T> extends Validation<T> {
//        TypeToken<T> type = new TypeToken<T>(getClass()) {
//        };
//
//        Type persistentClass;
//
//        public MyType() {
//            this.persistentClass = ((ParameterizedType) getClass()
//                    .getGenericSuperclass()).getActualTypeArguments()[0];
//        }
//
//        TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
//        };
//        Type type = typeToken.getType();
//    }


//    public static <T> Validation<String> get(Predicate<String> pred) {
//        Validation val =  new Validation(pred);
//        val.type = findSuperClassParameterType(val, Validation.class, 0);
//        return val;
//    }

    public Validation(TPred<? super T> tPred) {
        this(tPred,null);
    }

    //    public Validation(Predicate<? super T> tPred, Class<T> type) {
//        this(tPred, type, null);
//    }
//
//
    public Validation(TPred<? super T> tPred, String name) {
        this(tPred, name, null);
    }

    //
    public Validation(TPred<? super T> tPred, String name, String description) {
        this.pred = tPred.pred;
        this.type = tPred.type;
        this.name = Objects.isNull(name) ? getClass().getName() : name;
        this.description = Objects.isNull(description) ? "" : description;

    }

    public boolean test(T data) {
        return pred.test(data);
    }

    public Validation<T> and(Validation<? super T> other) {
        Objects.requireNonNull(other);
        return new Validation<>(new TPred<T>((t) -> test(t) && other.test(t)) {
        }, getName() + " & " + other.getName());
    }

    public Validation<T> or(Validation<? super T> other) {
        Objects.requireNonNull(other);
        return new Validation<>(new TPred<T>((t) -> test(t) || other.test(t)) {
        }, getName() + " | " + other.getName());
    }


    public Type getType() {
        return this.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static void main(String[] args) {
//        Validation<String> v = new Validation<String>(s -> s.contains("a"), String.class);
        Validation<String> v = new Validation<>(new TPred<String>(s -> s.contains("a")) {
        });
        System.out.println(v.getType());
    }
}
