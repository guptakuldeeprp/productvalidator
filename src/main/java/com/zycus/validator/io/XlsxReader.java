package com.zycus.validator.io;

import com.zycus.validator.core.data.Cell;
import com.zycus.validator.core.data.DataCell;
import com.zycus.validator.core.data.DataRow;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.jooq.lambda.tuple.Tuple;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class XlsxReader implements Closeable, AutoCloseable {

//    private ValidationService validationService = new BasicValidationService();

    /**
     * Stream returned keeps underlying input stream open
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private InputStream inputStream;
    /* is it already read */
    private boolean read;


    public XlsxReader(InputStream is) {
        this.inputStream = is;
        read = false;
    }

    public Stream<DataRow> readRows(final String sheetName) throws IOException {
        checkState();
        ReadableWorkbook wb = new ReadableWorkbook(inputStream);
        return readRows(readSheet(wb, sheetName));
    }

    public Stream<DataRow> readRows(final int sheetNum) throws IOException {
        checkState();
        ReadableWorkbook wb = new ReadableWorkbook(inputStream);
        return readRows(readSheet(wb, sheetNum));
    }

    public Stream<Cell> readColumn(int sheetNum, int column) throws IOException {
        checkState();
        ReadableWorkbook wb = new ReadableWorkbook(inputStream);
        return readSheet(wb, sheetNum).openStream().map(r -> getCell(r, column));
    }

    public Stream<Cell> readColumn(String sheetName, int column) throws IOException {
        checkState();
        ReadableWorkbook wb = new ReadableWorkbook(inputStream);
        return readSheet(wb, sheetName).openStream().map(r -> getCell(r, column));
    }

    public Stream<Tuple> readColumns(String sheetName, int... columns) throws IOException {
        if (columns.length < 2 || columns.length > 4)
            throw new IllegalArgumentException("Invalid columns count " + columns.length + ". Expected range [2,4]");
        checkState();
        ReadableWorkbook wb = new ReadableWorkbook(inputStream);
        return readSheet(wb, sheetName).openStream().map(r -> {
            Cell cells[] = getCells(r, columns);
            switch (cells.length) {
                case 2:
                    return Tuple.tuple(cells[0], cells[1]);
                case 3:
                    return Tuple.tuple(cells[0], cells[1], cells[2]);
                case 4:
                    return Tuple.tuple(cells[0], cells[1], cells[2], cells[3]);
                default: // should not reach here
                    throw new IllegalArgumentException("Invalid columns count " + columns.length + ". Expected range [2,4]");
            }

        });
    }

    public Stream<Sheet> readSheets() throws IOException {
        checkState();
        return new ReadableWorkbook(inputStream).getSheets();
    }

    private Sheet readSheet(ReadableWorkbook wb, String sheetName) {
        return wb.findSheet(sheetName).orElseThrow(() -> new IllegalArgumentException("Sheet " + sheetName + " does not exist"));
    }

    private Sheet readSheet(ReadableWorkbook wb, int sheetNum) {
        return wb.getSheet(sheetNum).orElseThrow(() -> new IllegalArgumentException("Sheet number " + sheetNum + " does not exist"));
    }

    private Stream<DataRow> readRows(Sheet sheet) throws IOException {
        return sheet.openStream().map(this::adapt);
    }

    private void checkState() {
        if (read) throw new IllegalStateException("The input stream is already read");
    }

    private Cell getCell(Row row, int index) {
        org.dhatim.fastexcel.reader.Cell _cell = row.getCell(index);
        return new DataCell(_cell, row.getRowNum(), _cell.getColumnIndex());
    }

    private Cell[] getCells(Row row, int... indices) {
        Cell[] cells = new Cell[indices.length];
        int i = 0;
        while (i < indices.length) {
            cells[i] = getCell(row, indices[i]);
            i++;
        }
        return cells;
    }


    private DataRow adapt(Row row) {
        Iterable<org.dhatim.fastexcel.reader.Cell> iterable = () -> row.iterator();
        Stream<org.dhatim.fastexcel.reader.Cell> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        List<Cell> dataCells = targetStream.map(cell -> new DataCell(cell.getValue(), row.getRowNum(), cell.getColumnIndex()))
                .collect(Collectors.toList());
        return new DataRow(dataCells, row.getRowNum());
    }

    public static void main(String[] args) {

    }


    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
