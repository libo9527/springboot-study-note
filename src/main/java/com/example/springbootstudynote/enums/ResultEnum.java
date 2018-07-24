package com.example.springbootstudynote.enums;

/**
 * @Description： 请求结果枚举，方便对请求结果的统一维护
 * @Auther： Administrator
 * @date： 2018/7/24:21:47
 */
public enum ResultEnum {

    SUCCESS(0, "成功"),
    UNKONW_ERROR(-1, "未知错误");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
