package com.excelmate;

import com.excelmate.domain.ExcelFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

class DataPopulator<T> {

    private final List<T> data;

    public DataPopulator(List<T> data) {
        this.data = data;
    }

    public void populate(Sheet sheet, Field[] fields, Workbook workbook) {
        int rowNum = 1;

        for (T item : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                try {
                    Object value = fields[i].get(item);
                    Cell cell = row.createCell(i);

                    cell.setCellValue(value == null ? "" : value.toString());

                    ExcelFont font = field.getAnnotation(ExcelFont.class);

                    if (font != null) {
                        cell.setCellStyle(FontMaker.bold(workbook.createFont(), workbook.createCellStyle(), font.bold()));
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("데이터 접근 오류", e);
                }
            }
        }
    }
}
