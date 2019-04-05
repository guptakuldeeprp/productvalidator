package com.zycus.validator.core;

import java.util.AbstractList;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LazySeq<E> extends AbstractList<E> {

    private E head;

    private LazySeq<? extends E> tail;

    private Supplier<LazySeq<E>> tailSup;

    public LazySeq(E head, Supplier<LazySeq<E>> tailSup) {
        this.head = head;
        this.tailSup = tailSup;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
