package com.ytb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Paper {

    private Integer paperId;

    private Integer userId;

    private String identifyingName;

    private String paperName;

    private String paperDesc;

    private Integer paperStatus;

    private String paperUrl;

    private Date createTime;

    private Date updateTime;

}
