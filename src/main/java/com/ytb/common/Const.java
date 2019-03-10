package com.ytb.common;

import lombok.Getter;

public class Const {

    public static final String CURRENT_USER = "current_user";

    @Getter
    public enum CommonEnum{

        SUCCESS(0,"操作成功"),
        FAIL(100,"操作失败"),
        NEED_LOGIN(101,"需要登录"),
        NO_POWER(102,"没有权限"),
        INPUT_NULL(103,"输入不能为空"),
        ;

        private int code;

        private String msg;

        CommonEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

    }

}
