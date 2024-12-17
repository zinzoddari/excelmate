package com.excelmate;

import com.excelmate.domain.ExcelHeader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 엑셀의 Sheet를 생성합니다.
 */
public final class SheetMate {

    /**
     * 엑셀을 생성합니다.
     * @param sheetName
     * @param data
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> byte[] generate(final String sheetName, final List<T> data) throws IOException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("데이터가 비어 있습니다.");
        }

        try (Workbook workbook = new SXSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(sheetName);

            final Class<?> dtoClass = data.get(0).getClass();
            final Field[] fields = dtoClass.getDeclaredFields();

            // 헤더 생성
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                ExcelHeader headerAnnotation = fields[i].getAnnotation(ExcelHeader.class);
                final String headerName = headerAnnotation != null ? headerAnnotation.value() : fields[i].getName();
                headerRow.createCell(i).setCellValue(headerName);
            }

            // 데이터 추가
            int rowNum = 1;
            for (T item : data) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    try {
                        Object value = fields[i].get(item);
                        row.createCell(i).setCellValue(value == null ? "" : value.toString());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("데이터 접근 오류", e);
                    }
                }
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
