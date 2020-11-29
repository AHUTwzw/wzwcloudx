package com.wzw.springcloud.service;

import com.wzw.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author 伍志伟
 * @create 2020-11-29 0:07
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPyamentById(@Param("id") Long id);
}
