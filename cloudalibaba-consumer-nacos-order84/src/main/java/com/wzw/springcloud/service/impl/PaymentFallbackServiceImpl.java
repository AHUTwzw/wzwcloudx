package com.wzw.springcloud.service.impl;

import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.rest.response.CommonResult;
import com.wzw.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @author wuzhiwei
 * @create 2020-12-06 20:44
 */
@Service
public class PaymentFallbackServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> PaymentSQL(Long id) {
        return new CommonResult<>(444444444, "服务降级返回，----PaymentFallbackServiceImpl", new Payment(id, "errorSerial"));
    }
}
