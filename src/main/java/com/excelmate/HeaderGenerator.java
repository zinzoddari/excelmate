package com.excelmate;

import com.excelmate.domain.ExcelHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;

class HeaderGenerator {

    void generateHeader(Row headerRow, final Field[] fields, Workbook workbook) {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Cell cell = headerRow.createCell(i);
            if (!field.isAnnotationPresent(ExcelHeader.class)) {
                continue;
            }

            ExcelHeader headerAnnotation = field.getAnnotation(ExcelHeader.class);
            final String headerName = headerAnnotation != null ? headerAnnotation.value() : field.getName();

            cell.setCellValue(headerName);

            assert headerAnnotation != null;
            if (headerAnnotation.bold()) {
                cell.setCellStyle(FontMaker.bold(workbook.createFont(), workbook.createCellStyle(), true));
            }
        }
    }
}
