package com.zycus.validator.core.data;

import java.util.Iterator;
import java.util.List;

public class DataRow implements Iterable<DataCell> {

    private List<DataCell> cells;

    public DataRow(List<DataCell> cells) {
        this.cells = cells;
    }

    @Override
    public Iterator<DataCell> iterator() {
        return cells.iterator();
    }
}
