package com.excelmate.dto;

import com.excelmate.domain.ExcelFont;
import com.excelmate.domain.ExcelHeader;

public class UserDto {

    @ExcelHeader(value = "아이디", bold = true)
    private String id;

    @ExcelHeader(value = "이메일", bold = true)
    private String email;

    @ExcelFont(bold = true)
    @ExcelHeader("이름")
    private String name;

    @ExcelHeader(value = "핸드폰 번호", bold = true)
    private String phone;

    public UserDto(String id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
