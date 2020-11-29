package com.wzw.springcloud.exception;

/**
 * @author wuzhiwei
 * @create 2020-11-29 2:27
 */
public class wzwCloudException extends Exception {
    /*无参构造函数*/
    public wzwCloudException(){
        super();
    }

    //用详细信息指定一个异常
    public wzwCloudException(String message){
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public wzwCloudException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public wzwCloudException(Throwable cause) {
        super(cause);
    }
}
