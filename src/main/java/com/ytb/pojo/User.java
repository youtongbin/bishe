package com.ytb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer role;

    private Date createTime;

    private Date updateTime;

}
