package com.wzw.springcloud.constant;

/**
 * @author wuzhiwei
 * @create 2020-11-29 2:45
 */
public interface TableConstant extends BaseConstant{

    /**
     * 用户表
     */
    interface User{
        public final String T_USER = "USER";
    }

    /**
     * 订单表
     */
    interface Payment{
        public final String T_PAYMENT = "PAYMENT";
    }
}
