package com.excelmate;

import com.excelmate.domain.ExcelFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import java.lang.reflect.Field;

public class FontMaker {

    public static CellStyle bold(final Field field, Font headerFont, CellStyle headerStyle) {
        ExcelFont fontAnnotation = field.getAnnotation(ExcelFont.class);
        final boolean isBold = fontAnnotation.bold();

        headerFont.setBold(isBold);

        headerStyle.setFont(headerFont);

        return headerStyle;
    }
}
