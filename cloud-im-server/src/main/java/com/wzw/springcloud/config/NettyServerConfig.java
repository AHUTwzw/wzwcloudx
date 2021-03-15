package com.wzw.springcloud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wuzhiwei
 * @create 2020-12-14 23:40
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyServerConfig {

    /**
     * 端口
     */
    private int port;
    /**
     * 最大线程数
     */
    private int maxThreads;
    /**
     * 最大数据包长度
     */
    private int maxFrameLength;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMaxFrameLength() {
        return maxFrameLength;
    }

    public void setMaxFrameLength(int maxFrameLength) {
        this.maxFrameLength = maxFrameLength;
    }
}
