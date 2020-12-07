package com.wzw.springcloud.controller;

import com.wzw.springcloud.domain.CommonResult;
import com.wzw.springcloud.domain.Order;
import com.wzw.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuzhiwei
 * @create 2020-12-07 21:16
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 测试链接
     * http://localhost:2001/order/create?userId=1&productId=1&count=10&money=100
     *
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建完成", order);
    }


}
