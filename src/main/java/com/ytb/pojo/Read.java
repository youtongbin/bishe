package com.ytb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Read {

    private Integer readId;

    private Integer paperId;

    private Integer userId;

    //审批意见
    private String readView;

    private Integer readStatus;

    private Date createTime;

    private Date updateTime;

}
