package com.wzw.springcloud.controller;

import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.rest.response.CommonResult;
import com.wzw.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuzhiwei
 * @create 2020-12-03 21:49
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * openfeign-ribbon 客户端一般默认等待1秒钟
     * 在支付服务侧,模拟暂停三秒
     *
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        //客户端默认等待1秒钟
        return paymentFeignService.paymentFeignTimeOut();
    }
}
