package com.wzw.springcloud.service;

import java.math.BigDecimal;

/**
 * @author wuzhiwei
 * @create 2020-12-07 21:30
 */
public interface AccountService {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     */
    void decrease(Long userId, BigDecimal money);
}
