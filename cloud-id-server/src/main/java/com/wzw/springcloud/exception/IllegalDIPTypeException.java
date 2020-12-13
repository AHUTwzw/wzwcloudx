package com.wzw.springcloud.exception;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:51
 */
public class IllegalDIPTypeException extends DidServiceException{
    //默认构造器
    public IllegalDIPTypeException() {
    }
    //带有详细信息的构造器，信息存储在message中
    public IllegalDIPTypeException(String message) {
        super(message);
    }
}
