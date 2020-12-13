package com.wzw.springcloud.controller;

import com.wzw.springcloud.exception.DidServiceException;
import com.wzw.springcloud.exception.IllegalDIPTypeException;
import com.wzw.springcloud.rest.response.CommonResult;
import com.wzw.springcloud.service.DIdContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-12-13 16:58
 */
@Slf4j
@RestController
@RequestMapping("/cloud/did")
@RefreshScope
public class DIdServiceController {

    @Autowired
    private DIdContextHolder didContext;

    @PostMapping("/getId")
    public CommonResult getId(@RequestParam("type") String type,
                              @RequestParam("strategy") String strategy,
                              @RequestParam("num") Integer num){
        List didList;
        try {
            if (num == null) {num = 1;}
            didList = didContext.getDidList(type, strategy, num);
            return new CommonResult(200, "success", didList);
        } catch (DidServiceException e) {
            log.error(e.getMessage());
            return new CommonResult(500, "fail", e.getMessage());
        }
    }
}
