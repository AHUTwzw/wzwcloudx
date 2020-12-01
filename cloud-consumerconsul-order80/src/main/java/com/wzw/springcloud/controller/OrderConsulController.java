package com.wzw.springcloud.controller;

import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author 伍志伟
 * @create 2020-11-29 1:31
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderConsulController {

    public static final String PAYMENT_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String getPaymentById() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul",
                String.class);
    }
}
