package com.excelmate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

    public static <T> ResponseEntity<byte[]> httpDownload(final String fileName, final String sheetName, final List<T> data) throws IOException {
        final byte[] content = SheetMate.generate(sheetName, data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        // 응답 객체 생성
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
