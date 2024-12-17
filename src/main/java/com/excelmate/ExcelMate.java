package com.excelmate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public final class ExcelMate {

    /**
     * 엑셀을 파일로 다운로드합니다.
     */
    public static <T> void download(final String fileName, final String sheetName, final List<T> data) throws IOException {
        final byte[] content = SheetMate.generate(sheetName, data);

        try (FileOutputStream fileOut = new FileOutputStream(fileName + ".xlsx")) {
            fileOut.write(content);
        }
    }
}
