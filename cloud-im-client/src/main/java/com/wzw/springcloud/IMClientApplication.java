package com.wzw.springcloud;

import com.wzw.springcloud.client.IMClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuzhiwei
 * @create 2020-12-14 1:08
 * 客户端
 * 1.为初始化客户端，创建一个Bootstrap实例
 * 2.为进行事件处理分配了一个NioEventLoopGroup实例，其中事件处理包括创建新的连接以及处理入站和出站数据；
 * 3.当连接被建立时，一个EchoClientHandler实例会被安装到（该Channel的一个ChannelPipeline中；
 * 4.在一切都设置完成后，调用Bootstrap.connect()方法连接到远程节点。
 */

@SpringBootApplication
public class IMClientApplication implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;
    @Value("${netty.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(IMClientApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        new IMClient(url,port).start();
    }
}
