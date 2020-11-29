package com.wzw.springcloud.controller;

import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.response.CommonResult;
import com.wzw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wuzhiwei
 * @create 2020-11-29 0:10
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody @Valid Payment payment) {
        int result = paymentService.create(payment);
        log.info("insert payment: {}", result);
        if (result > 0) {
            return new CommonResult(200, "success", result);
        }else {
            return new CommonResult(500, "fail", result);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPyamentById(id);
        log.info("getPyamentById: {}", payment);
        if (payment != null) {
            return new CommonResult(200, "success", payment);
        }else {
            return new CommonResult(500, "fail", payment);
        }
    }
}
