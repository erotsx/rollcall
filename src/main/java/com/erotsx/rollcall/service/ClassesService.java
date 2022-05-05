package com.erotsx.rollcall.service;

import com.erotsx.rollcall.dao.ClassesDao;
import com.erotsx.rollcall.entity.AjaxResult;
import com.erotsx.rollcall.entity.Classes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ClassesService {

    @Resource
    private ClassesDao classesDao;

    public AjaxResult addClass(String className) {
        if (classesDao.getIdByName(className) != null) {
            return AjaxResult.doError("班级已存在");
        }
        classesDao.addClass(className);
        return AjaxResult.doSuccess();
    }

    public List<Classes> getAllClasses() {
        return classesDao.getAllClasses();
    }
}
