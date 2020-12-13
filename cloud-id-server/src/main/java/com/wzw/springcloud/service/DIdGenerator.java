package com.wzw.springcloud.service;

import com.wzw.springcloud.exception.DidServiceException;

import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:00
 */
public interface DIdGenerator {
    List getId(String strategy, int num) throws DidServiceException;
}
