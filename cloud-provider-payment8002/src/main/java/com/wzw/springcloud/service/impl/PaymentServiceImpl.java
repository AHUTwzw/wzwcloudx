package com.wzw.springcloud.service.impl;

import com.wzw.springcloud.dao.PaymentDao;
import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 伍志伟
 * @create 2020-11-29 0:04
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPyamentById(@Param("id") Long id) {
        return paymentDao.getPyamentById(id);
    }
}
