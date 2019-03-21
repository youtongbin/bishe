package com.ytb.common;

import lombok.Getter;

public class Const {

    public static final String CURRENT_USER = "current_user";

    public static final String TEMP_KEEP = "D:/upload_test";


    @Getter
    public enum ReadStatusEnum{

        SUCCESS(0,"已通过"),
        FAIL(1,"未通过"),
        ;

        private int code;

        private String msg;

        ReadStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static ReadStatusEnum codeOf(Integer code){
            for (ReadStatusEnum r:values()
            ) {
                if (r.code == code){
                    return r;
                }
            }
            return null;
        }
    }


    @Getter
    public enum PaperStatusEnum{

        SUCCESS(0,"已通过"),
        FAIL(1,"未通过"),
        UN_HANDLE(2,"待审核"),
        ;

        private int code;

        private String msg;

        PaperStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static PaperStatusEnum codeOf(Integer code){
            for (PaperStatusEnum p:values()
                 ) {
                if (p.code == code){
                    return p;
                }
            }
            return null;
        }
    }


    @Getter
    public enum ApplyStatusEnum{

        SUCCESS(0,"已通过"),
        FAIL(1,"未通过"),
        UN_HANDLE(2,"待审批"),
        ;

        private int code;

        private String msg;

        ApplyStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static ApplyStatusEnum codeOf(Integer code){
            for (ApplyStatusEnum a:values()
            ) {
                if (a.code == code){
                    return a;
                }
            }
            return null;
        }
    }

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
