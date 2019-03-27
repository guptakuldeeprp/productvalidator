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

    /**
     * Stream returned keeps underlying input stream open
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public Stream<DataRow> readRows(String filePath) throws IOException {

        InputStream is = new FileInputStream(filePath);
        ReadableWorkbook wb = new ReadableWorkbook(is);

        Sheet sheet = wb.getFirstSheet();
        return sheet.openStream().map(this::adapt);

    }

    private DataRow adapt(Row row) {
        Iterable<Cell> iterable = () -> row.iterator();
        Stream<Cell> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        List<DataCell> dataCells = targetStream.map(cell -> new DataCell(cell.getValue(), cell.getColumnIndex()))
                .collect(Collectors.toList());
        return new DataRow(dataCells, row.getRowNum());
    }

    public static void main(String[] args) {

    }


}
