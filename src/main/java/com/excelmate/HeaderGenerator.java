package com.excelmate;

import com.excelmate.domain.ExcelFont;
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
            if (field.isAnnotationPresent(ExcelHeader.class)) {
                ExcelHeader headerAnnotation = field.getAnnotation(ExcelHeader.class);
                final String headerName = headerAnnotation != null ? headerAnnotation.value() : field.getName();

                cell.setCellValue(headerName);
            }

            if (field.isAnnotationPresent(ExcelFont.class)) {
                cell.setCellStyle(FontMaker.bold(field, workbook.createFont(), workbook.createCellStyle()));
            }
        }
    }
}
