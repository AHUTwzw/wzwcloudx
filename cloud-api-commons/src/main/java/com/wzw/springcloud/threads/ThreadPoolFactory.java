package com.wzw.springcloud.threads;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wuzhiwei
 * @create 2020-12-13 18:05
 */
public class ThreadPoolFactory {
    private static Map threadPoolMap = new HashMap<String, String>();

    private static ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void init() {

   }
}
