package com.wzw.springcloud.exception;

/**
 * @author wuzhiwei
 * @create 2020-12-15 0:09
 */
public class ErrorParamsException extends RuntimeException {
    private static final long serialVersionUID = -623198335011996153L;

    public ErrorParamsException() {
        super();
    }

    public ErrorParamsException(String message) {
        super(message);
    }
}