package com.ytb.vo;

import lombok.Data;

@Data
public class ReadVO {

    private Integer readId;

    private Integer paperId;

    private String paperName;

    private Integer userId;

    private String username;

    //审批意见
    private String readView;

    private Integer readStatus;

    private String readStatusDesc;

    private String createTime;

    private String updateTime;

}
