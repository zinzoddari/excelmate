package com.excelmate;

import com.excelmate.dto.UserDto;
import java.io.File;
import java.nio.file.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ExcelMateTest {
    private static final String FILE_NAME = "temp.xlsx";

    private List<UserDto> exampleList;
    private final ExcelMate excelMate = new ExcelMate();

    @BeforeEach
    void init() {
        exampleList = List.of(
                new UserDto("1", "a@a.com", "홍길동", "010-1234-1234")
        );
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(new File(FILE_NAME).toPath());
    }


    @Test
    void download() throws IOException {
        //given
        OutputStream outputStream = new FileOutputStream(FILE_NAME);

        //when
        excelMate.download("sheetName", UserDto.class, exampleList, outputStream);
    }

    @Test
    void tempDownload() throws IOException {
        //when
        String result = excelMate.download("temrp", UserDto.class, exampleList);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    void tempDelete() throws IOException {
        //given
        String path = excelMate.download("temrp", UserDto.class, exampleList);

        //when & then
        assertDoesNotThrow(() -> excelMate.removeTempFile(path));
    }
}
