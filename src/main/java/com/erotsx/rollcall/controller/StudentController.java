package com.erotsx.rollcall.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.erotsx.rollcall.entity.AjaxResult;
import com.erotsx.rollcall.entity.Classes;
import com.erotsx.rollcall.entity.Student;
import com.erotsx.rollcall.listener.StudentDataListener;
import com.erotsx.rollcall.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/addStudents")
    public AjaxResult addStudents(MultipartFile file, String classId) throws IOException {
        EasyExcel.read(file.getInputStream(), Student.class, new StudentDataListener(studentService, Integer.valueOf(classId))).sheet().doRead();
        return AjaxResult.doSuccess("success");
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(String className) {
        return studentService.getStudents(className);
    }

    @GetMapping("/getNameList")
    public AjaxResult getNameList(Integer classId) {
        return AjaxResult.doSuccess(studentService.getNameList(classId));
    }


}
