package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhiwei
 * @create 2021-03-13 22:52
 */
@RestController
@RequestMapping(value ="/test")
public class TestController {
    @GetMapping(value ="/getTime")
    public Long getTime() {
        return System.currentTimeMillis();
    }
}
