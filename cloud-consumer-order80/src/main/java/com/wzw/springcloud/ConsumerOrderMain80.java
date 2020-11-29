package com.wzw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wuzhiwei
 * @create 2020-11-29 1:26
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain80.class, args);
    }
}