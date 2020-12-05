package com.wzw.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhiwei
 * @create 2020-12-05 19:25
 */
@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {
    //    @Value("${server.port}")
    private String serverPort="999";

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo() {
        return "serverPort: " + serverPort + "\t\n\n configInfo: " + configInfo;
    }

}
