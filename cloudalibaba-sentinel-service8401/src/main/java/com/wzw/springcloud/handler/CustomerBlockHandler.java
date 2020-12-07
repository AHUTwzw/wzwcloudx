package com.wzw.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wzw.springcloud.rest.response.CommonResult;

/**
 * @author wuzhiwei
 * @create 2020-12-07 0:16
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客户自定义,global handlerException----------1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义2,global handlerException----------2");
    }
}