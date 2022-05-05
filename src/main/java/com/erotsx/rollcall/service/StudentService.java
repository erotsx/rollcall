package com.erotsx.rollcall.service;

import com.erotsx.rollcall.dao.StudentDao;
import com.erotsx.rollcall.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;

    public void addStudents(List<Student> students) {
        studentDao.addStudents(students);
    }

    public List<Student> getStudents(String className) {
        return studentDao.getStudents(className);
    }

    public List<String> getNameList(Integer classId) {
        return studentDao.getNameList(classId);
    }

    public void delStudents(String classId) {
        studentDao.delStudents(classId);
    }
}
