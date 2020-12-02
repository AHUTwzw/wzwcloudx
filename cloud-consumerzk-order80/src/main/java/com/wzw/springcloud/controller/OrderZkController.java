package com.wzw.springcloud.controller;

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
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("payment/zk")
    public String getPaymentZk() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk",
                String.class);
    }
}
