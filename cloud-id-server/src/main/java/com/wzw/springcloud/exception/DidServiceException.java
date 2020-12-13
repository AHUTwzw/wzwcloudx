package com.wzw.springcloud.exception;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:51
 */
public class DidServiceException extends Exception{
    //默认构造器
    public DidServiceException() {
    }
    //带有详细信息的构造器，信息存储在message中
    public DidServiceException(String message) {
        super(message);
    }
}
