package com.ytb.vo;

import lombok.Data;

@Data
public class ApplyVO {

    private Integer applyId;

    private Integer userId;

    private Integer applyStatus;

    private String applyStatusDesc;

    private String createTime;

    private String updateTime;

}
