package com.erotsx.rollcall.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysUser {

    private Integer id;

    private String username;

    private String password;

    private String status;

    private Date regDate;

    private List<SysRole> roles;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
