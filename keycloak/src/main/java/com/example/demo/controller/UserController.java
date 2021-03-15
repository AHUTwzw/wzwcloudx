package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wuzhiwei
 * @create 2021-03-13 22:52
 */
@RestController
@RequestMapping(value ="/user")
public class UserController {

    @GetMapping(value ="/login")
    public String login() {
        return "success";
    }

}
