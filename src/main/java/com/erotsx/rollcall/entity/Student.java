package com.erotsx.rollcall.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Student {

    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    private String gender;

    private Integer classId;
}
