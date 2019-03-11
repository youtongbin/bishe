package com.ytb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Apply {

    private Integer applyId;

    private Integer userId;

    private Integer applyStatus;

    private Date createTime;

    private Date updateTime;

}
