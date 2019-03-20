package com.ytb.vo;

import lombok.Data;

@Data
public class UserVO {

    private Integer userId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer role;

    private String roleDesc;

    private String createTime;

    private String updateTime;

}
