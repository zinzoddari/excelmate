package com.excelmate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public final class ExcelMate {

    private <T> void create(final String sheetName, final Class<T> tClass, final List<T> data, OutputStream outputStream) throws IOException {
        final SheetMate sheetMate = new SheetMate(tClass, data);
        sheetMate.generate(sheetName, outputStream);
    }

    public <T> void download(final String sheetName, final Class<T> tClass, final List<T> data, OutputStream outputStream) throws IOException {
        create(sheetName, tClass, data, outputStream);
    }

    public <T> String download(final String sheetName, final Class<T> tClass, final List<T> data) throws IOException {
        final String tempDirPath = System.getProperty("java.io.tmpdir") + "excelmate";
        final File tempDir = new File(tempDirPath);

        if (!tempDir.exists()) {
            boolean result = tempDir.mkdir();
            if (!result) {
                throw new IOException("디렉터리 생성 중 오류 발생");
            }
        }

        File tempFile = File.createTempFile("temp-", ".xlsx", tempDir);

        OutputStream outputStream = new FileOutputStream(tempFile);

        create(sheetName, tClass, data, outputStream);

        return tempFile.getPath();
    }

    public void removeTempFile(final String path) {
        final File tempDir = new File(path);

        tempDir.delete();
    }
}
