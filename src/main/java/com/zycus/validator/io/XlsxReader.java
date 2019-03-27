package com.zycus.validator.io;

import com.zycus.validator.core.BasicValidationService;
import com.zycus.validator.core.ValidationService;
import com.zycus.validator.core.data.DataCell;
import com.zycus.validator.core.data.DataRow;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class XlsxReader {

//    private ValidationService validationService = new BasicValidationService();

    public Stream<DataRow> readRows(String filePath) throws IOException {

        try (InputStream is = new FileInputStream(filePath); ReadableWorkbook wb = new ReadableWorkbook(is)) {

            Sheet sheet = wb.getFirstSheet();
            return sheet.openStream().map(this::adapt);
        }
    }

    private DataRow adapt(Row row) {
        Iterable<Cell> iterable = () -> row.iterator();
        Stream<Cell> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        List<DataCell> dataCells = targetStream.map(cell -> new DataCell(cell.getValue()))
                .collect(Collectors.toList());
        return new DataRow(dataCells);
    }

    public static void main(String[] args) {

    }


}
