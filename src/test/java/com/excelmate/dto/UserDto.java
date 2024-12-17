package com.excelmate.dto;

import com.excelmate.domain.ExcelHeader;

public class UserDto {

    @ExcelHeader("아이디")
    private String id;

    @ExcelHeader("이메일")
    private String email;

    @ExcelHeader("이름")
    private String name;

    @ExcelHeader("핸드폰 번호")
    private String phone;

    public UserDto(String id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
