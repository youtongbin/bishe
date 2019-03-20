package com.ytb.vo;

import lombok.Data;

import java.util.List;

@Data
public class FunctionVO {

    private Integer functionId;

    private String functionName;

    private Integer parentId;

    private Integer role;

    private String url;

    private List<FunctionVO> functionVOList;

}
