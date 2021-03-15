package com.wzw.springcloud.exception;

/**
 * @author wuzhiwei
 * @create 2020-12-15 0:10
 */
public class NoUseableChannel extends RuntimeException{
    private static final long serialVersionUID = 7762465537123947683L;

    public NoUseableChannel() {
        super();
    }

    public NoUseableChannel(String message) {
        super(message);
    }
}

