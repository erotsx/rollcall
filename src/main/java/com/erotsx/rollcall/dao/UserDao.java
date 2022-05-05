package com.erotsx.rollcall.dao;

import com.erotsx.rollcall.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    SysUser findByUserName(String username);

    int register(SysUser user);

    int setRole(int id);
}
