package com.zycus.validator.io;

import com.zycus.validator.core.BasicValidationService;
import com.zycus.validator.core.ValidationService;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class XlslReader {

    private ValidationService validationService = new BasicValidationService();

    public void readRows(String filePath) throws IOException {

        try (InputStream is = new FileInputStream(filePath); ReadableWorkbook wb = new ReadableWorkbook(is)) {

            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {

            }
        }
    }

}
