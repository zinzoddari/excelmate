package com.excelmate;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class FontMaker {

    public static CellStyle bold(final Workbook workbook, final boolean isBold) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(isBold);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        return headerStyle;
    }
}
