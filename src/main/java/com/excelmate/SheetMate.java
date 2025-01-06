package com.excelmate;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 엑셀의 Sheet를 생성합니다.
 */
public final class SheetMate<T> {
    private final Class<T> dtoClass;
    private final List<T> data;

    private final HeaderGenerator headerGenerator;
    private final DataPopulator dataPopulator;

    public SheetMate(Class<T> dtoClass, List<T> data) {
        this.dtoClass = dtoClass;
        this.data = data;
        this.headerGenerator = new HeaderGenerator();
        this.dataPopulator = new DataPopulator(data);
    }

    /**
     * 엑셀을 생성합니다.
     */
    public void generate(final String sheetName, final OutputStream outputStream) throws IOException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("데이터가 비어 있습니다.");
        }

        try (Workbook workbook = new SXSSFWorkbook(); outputStream) {

            Sheet sheet = workbook.createSheet(sheetName);

            final Field[] fields = dtoClass.getDeclaredFields();

            // 헤더 생성
            headerGenerator.generateHeader(sheet.createRow(0), fields, workbook);

            // 데이터 추가
            dataPopulator.populate(sheet, fields);

            workbook.write(outputStream);
        }
    }
}
