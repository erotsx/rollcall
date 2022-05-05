package com.erotsx.rollcall.controller;

import com.erotsx.rollcall.entity.AjaxResult;
import com.erotsx.rollcall.entity.Classes;
import com.erotsx.rollcall.service.ClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Resource
    private ClassesService classesService;

    @PostMapping("/addClass")
    public AjaxResult addClass(String className) {
        return classesService.addClass(className);
    }

    @GetMapping("/getAllClasses")
    public List<Classes> getAllClasses() {
        return classesService.getAllClasses();
    }
}
