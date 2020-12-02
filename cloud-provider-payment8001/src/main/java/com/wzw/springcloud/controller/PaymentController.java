package com.wzw.springcloud.controller;

import com.wzw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.wzw.springcloud.entities.Payment;
import com.wzw.springcloud.rest.response.CommonResult;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-11-29 0:10
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody @Valid Payment payment) {
        int result = paymentService.create(payment);
        log.info("insert payment: {}", result);
        if (result > 0) {
            return new CommonResult(200, "success", result);
        }else {
            return new CommonResult(500, "fail", result);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPyamentById(id);
        log.info("getPyamentById: {}", payment);
        if (payment != null) {
            return new CommonResult(200, "success", payment);
        }else {
            return new CommonResult(500, "fail", payment);
        }
    }

    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> {
            log.info("----------element:" + service);
        });

        List<ServiceInstance> instances = discoveryClient
                .getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> {
            log.info("serviceId:[{}], 主机名称:[{}], 端口号:[{}], url地址:[{}]",
                    instance.getServiceId(), instance.getHost(),
                    instance.getPort(), instance.getUri());
        });
        return this.discoveryClient;
    }

    /**
     * ribbon负载均衡 rule
     *
     * @return
     */
    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
