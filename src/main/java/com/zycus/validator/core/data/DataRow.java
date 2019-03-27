package com.zycus.validator.core.data;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataRow implements Iterable<DataCell> {

    private final int pos;

    private final List<DataCell> cells;

    public DataRow(List<DataCell> cells) {
        this.cells = cells;
        pos = -1;
    }

    public DataRow(List<DataCell> cells, int pos) {
        this.pos = pos;
        this.cells = cells;
    }

    public DataCell getCell(int i) {
        return cells.get(i);
    }

    public List<DataCell> getCells(int start, int end) {
        return Collections.unmodifiableList(cells.subList(start, end));
    }

    public List<DataCell> getCells() {
        return Collections.unmodifiableList(cells);
    }


    @Override
    public Iterator<DataCell> iterator() {
        return cells.iterator();
    }

    public int getPos() {
        return pos;
    }
}
