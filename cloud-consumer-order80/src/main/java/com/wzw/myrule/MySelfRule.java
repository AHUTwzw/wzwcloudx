package com.wzw.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhiwei
 * @create 2020-12-02 0:30
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        //定义为随机
        return new RandomRule();
    }
}
