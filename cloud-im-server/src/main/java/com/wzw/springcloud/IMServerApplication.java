package com.wzw.springcloud;

import com.wzw.springcloud.server.IMServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuzhiwei
 * @create 2020-12-14 1:08
 */
@SpringBootApplication
public class IMServerApplication implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;
    @Value("${netty.url}")
    private String url;
    @Autowired
    private IMServer imServer;

    public static void main(String[] args) {
        SpringApplication.run(IMServerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = imServer.start(url,port);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                imServer.destroy();
            }
        });
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        future.channel().closeFuture().syncUninterruptibly();
    }
}
