package com.wzw.springcloud.constant;

/**
 * @author wuzhiwei
 * @create 2020-11-29 2:37
 */
public enum Result {
    SUCCESS(1001, "success"),
    FAIL(1002, "fail"),
    INTERRUPT(1003, "interrupt"),
    UNKNOWN(1004, "nuknown");

    private Integer code;
    private String msg;

    private Result(int code,String msg) {
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
