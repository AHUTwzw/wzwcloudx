package com.wzw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wuzhiwei
 * @create 2020-12-13 16:56
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DIdServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DIdServiceApplication.class);
    }
}
