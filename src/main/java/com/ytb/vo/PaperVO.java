package com.ytb.vo;

import lombok.Data;

@Data
public class PaperVO {

    private Integer paperId;

    private Integer userId;

    private String identifyingName;

    private String paperName;

    private String paperDesc;

    private Integer paperStatus;

    private String paperStatusDesc;

    private String paperUrl;

    private String createTime;

    private String updateTime;

}
