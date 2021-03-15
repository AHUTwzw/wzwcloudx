package com.wzw.springcloud.server;

import com.wzw.springcloud.adapter.ServerChannelHandlerAdapter;
import com.wzw.springcloud.config.NettyServerConfig;
import com.wzw.springcloud.utils.ObjectCodec;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzhiwei
 * @create 2020-12-15 0:42
 */
@Slf4j
@Component
public class IMServer {
    /**
     * 创建bootstrap
     */
    private ServerBootstrap serverBootstrap = new ServerBootstrap();
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    /**
     * NETT服务器配置类
     */
    @Resource
    private NettyServerConfig nettyConfig;

    /**
     * 通道适配器
     */
    @Resource
    private ServerChannelHandlerAdapter channelHandlerAdapter;

    public ChannelFuture start(String hostname, int port) {
        ChannelFuture f = null;
        try {
            //ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(hostname,port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            为监听客户端read/write事件的Channel添加用户自定义的ChannelHandler
                            socketChannel.pipeline()
                                    .addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                                    .addLast(new LengthFieldBasedFrameDecoder(nettyConfig.getMaxFrameLength()
                                            , 0, 2, 0, 2))
                                    .addLast(new LengthFieldPrepender(2))
                                    .addLast(new ObjectCodec())
                                    .addLast(channelHandlerAdapter);
                        }
                    });
            log.info("IM服务器在[{}]端口启动监听", port);
            f = serverBootstrap.bind().sync();
            channel = f.channel();
            log.info("======IMServer启动成功!!!=========");
        } catch (Exception e) {
            log.info("[出现异常] 释放资源");
            destroy();
        } finally {
            if (f != null && f.isSuccess()) {
                log.info("IM server listening " + hostname + " on port " + port + " and ready for connections...");
            } else {
                log.error("IM server start up Error!");
            }
        }
        return f;
    }

    public void destroy() {
        log.info("Shutdown IM Server...");
        if(channel != null) { channel.close();}
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown IM Server Success!");
    }
}
