package com.excelmate;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class FontMaker {

    public static CellStyle bold(Font headerFont, CellStyle headerStyle, final boolean isBold) {
        headerFont.setBold(isBold);

        headerStyle.setFont(headerFont);

        return headerStyle;
    }
}
