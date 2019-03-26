package com.ytb.vo;

import com.ytb.dao.UserDao;
import lombok.Data;

import javax.annotation.Resource;

@Data
public class PaperVO {

    private Integer paperId;

    private Integer userId;

    private String username;

    private String identifyingName;

    private String paperName;

    private String paperDesc;

    private Integer paperStatus;

    private String paperStatusDesc;

    private String paperUrl;

    private String createTime;

    private String updateTime;

}
