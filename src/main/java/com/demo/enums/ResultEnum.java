package com.demo.enums;

/**
 * Created by liyong on 2017/4/8.
 */
public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    MINOR(100, "这个人还是未成年"),
    MIDLIFE(101, "这个人已经成年，但还没有步入老年"),
    ;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
