package com.wzw.springcloud.service;

/**
 * @author wuzhiwei
 * @create 2020-12-07 21:30
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
