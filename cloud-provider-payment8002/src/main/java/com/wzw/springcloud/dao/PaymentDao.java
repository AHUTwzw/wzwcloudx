package com.wzw.springcloud.dao;

import com.wzw.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wuzhiwei
 * @create 2020-11-28 23:53
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPyamentById(@Param("id") Long id);
}
