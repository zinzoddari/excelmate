package com.excelmate;

import com.excelmate.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ExcelMateTest {
    private List<UserDto> exampleList;
    private ExcelMate excelMate = new ExcelMate();

    @BeforeEach
    void init() {
        exampleList = List.of(
                new UserDto("1", "a@a.com", "홍길동", "010-1234-1234")
        );
    }

    @Test
    void download() throws IOException {
        //given
        OutputStream outputStream = new FileOutputStream("temp.xlsx");

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
