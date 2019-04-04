package com.zycus.validator.core.data;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataRow implements Iterable<Cell> {

    private final int pos;

    private final List<Cell> cells;

    public DataRow(List<Cell> cells) {
        this.cells = cells;
        pos = -1;
    }

    public DataRow(List<Cell> cells, int pos) {
        this.pos = pos;
        this.cells = cells;
    }

    public Cell getCell(int i) {
        return cells.get(i);
    }

    public List<Cell> getCells(int start, int end) {
        return Collections.unmodifiableList(cells.subList(start, end));
    }

    public List<Cell> getCells() {
        return Collections.unmodifiableList(cells);
    }


    @Override
    public Iterator<Cell> iterator() {
        return cells.iterator();
    }

    public int getPos() {
        return pos;
    }

    public boolean isEmpty() {
        return getCells().isEmpty();
    }
}
