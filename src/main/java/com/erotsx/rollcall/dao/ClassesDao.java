package com.erotsx.rollcall.dao;

import com.erotsx.rollcall.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassesDao {
    void addClass(String className);

    Integer getIdByName(String className);

    List<Classes> getAllClasses();
}
