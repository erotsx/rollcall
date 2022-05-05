package com.erotsx.rollcall.dao;

import com.erotsx.rollcall.entity.Student;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface StudentDao {
    void addStudents(List<Student> students);

    List<Student> getStudents(String className);

    List<String> getNameList(Integer classId);
}
