package com.ytb.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse <T> {

    private int code;
    private T data;
    private String msg;

    private ServerResponse(int code)
    {
        this.code = code;
    }

    private ServerResponse(int code, T data)
    {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(int code, T data, String msg)
    {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ServerResponse serverResponseBySuccess()
    {
        return new ServerResponse(Const.CommonEnum.SUCCESS.getCode());
    }

    public static <T> ServerResponse serverResponseBySuccess(T data)
    {
        return new ServerResponse(Const.CommonEnum.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse serverResponseBySuccess(T data, String msg)
    {
        return new ServerResponse(Const.CommonEnum.SUCCESS.getCode(), data, msg);
    }

    public static <T> ServerResponse serverResponseBySuccess(int code, T data, String msg)
    {
        return new ServerResponse(code, data, msg);
    }

    public static ServerResponse serverResponseByFail()
    {
        return new ServerResponse(Const.CommonEnum.FAIL.getCode());
    }

    public static ServerResponse serverResponseByFail(String msg)
    {
        return new ServerResponse(Const.CommonEnum.FAIL.getCode(), msg);
    }

    public static ServerResponse serverResponseByFail(int code)
    {
        return new ServerResponse(code);
    }

    public static ServerResponse serverResponseByFail(int code, String msg)
    {
        return new ServerResponse(code, msg);
    }

    @JsonIgnore
    public boolean isSuccess()
    {
        return code == Const.CommonEnum.SUCCESS.getCode();
    }

}
