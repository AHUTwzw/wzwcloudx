package com.wzw.springcloud.client;

import com.wzw.springcloud.handler.IMClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author wuzhiwei
 * @create 2020-12-15 20:18
 */
@Slf4j
public class IMClient {
    private final String host;
    private final int port;
    public IMClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        /**
         * Netty用于接收客户端请求的线程池职责如下。
         * （1）接收客户端TCP连接，初始化Channel参数；
         * （2）将链路状态变更事件通知给ChannelPipeline
         */
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new JsonObjectDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));// String解码。
                            socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));// String解码。
                            socketChannel.pipeline().addLast(new IMClientHandler());
                        }
                    });
            //绑定端口
            System.out.println("客户端连接服务器...");
            ChannelFuture f = b.connect(host, port).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        // 第一种方法。
                        // Netty在这里发送数据。
                         sendDataToServer(channelFuture.channel());
                    }

                }
            }).sync();

            f.channel().closeFuture().sync();
            //接收服务端返回的数据
            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            Object result = f.channel().attr(key).get();
            System.out.println(result.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            group.shutdownGracefully().sync();
        }
    }

    private void sendDataToServer(Channel channel) throws Exception {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd,HH:mm:ss:SSS");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            channel.writeAndFlush("客户端@" + scanner.nextLine() + sdf.format(date));
            System.out.println("客户端发送数据:" + scanner.nextLine() + sdf.format(date));
            Thread.sleep(1000);
        }
    }
}
