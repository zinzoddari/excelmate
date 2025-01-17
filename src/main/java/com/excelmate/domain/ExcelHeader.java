package com.excelmate.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.core.annotation.AliasFor;

@ExcelFont
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelHeader {
    String value();

    @AliasFor(annotation = ExcelFont.class, attribute = "bold")
    boolean bold() default false;
}

