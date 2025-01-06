package com.excelmate;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.List;

class DataPopulator<T> {

    private final List<T> data;

    public DataPopulator(List<T> data) {
        this.data = data;
    }

    public void populate(Sheet sheet, Field[] fields) {
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
    }
}
