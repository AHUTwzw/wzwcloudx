package com.wzw.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhiwei
 * @create 2020-12-07 21:27
 */
@Configuration
@MapperScan("com.wzw.springcloud.dao")
public class MyBatisConfig {
}
