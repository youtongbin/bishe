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

    @Getter
    public enum PowerEnum{

        MANAGER(0,"管理员"),
        SPECIALIST(1,"专家老师"),
        COMMON(2,"普通用户"),
        ;

        private Integer code;

        private String msg;

        PowerEnum(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static PowerEnum codeOf(Integer code){
            for (PowerEnum p:values()
                 ) {
                if (p.code == code){
                    return p;
                }
            }
            return null;
        }
    }


}
