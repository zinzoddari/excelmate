package com.excelmate.example;

import com.excelmate.ExcelMate;
import com.excelmate.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelDownloadTest {

    private final static String FILE_NAME = "회원 현황";

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(new File(FILE_NAME + ".xlsx").toPath());
    }

    @Test
    @DisplayName("엑셀 파일을 다운로드 받습니다.")
    void download() throws IOException {
        //given
        final List<UserDto> users = List.of(
                new UserDto("1", "a@a.com", "홍길동", "010-1234-1234")
        );

        //when
        assertDoesNotThrow(() -> ExcelMate.download(FILE_NAME, "회원 현황", users));
    }
}
