package com.hisen.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * @author hisenyuan
 * @time 2018/7/31 10:04
 * @description
 */
public class NettyWebSocketServer {
    private io.netty.channel.Channel channel;
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static int port = 9999;

    public NettyWebSocketServer(int port) {
    }

    public void run(){
        try {
            //创建ServerBootstrap实例
            ServerBootstrap b = new ServerBootstrap();
            //设置并绑定Reactor线程池
            b.group(bossGroup, workerGroup)
                    //设置并绑定服务端Channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch){
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-codec", new HttpServerCodec());
                            // Http消息组装
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                            // WebSocket通信支持
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                            //自定义处理类
                            pipeline.addLast(new SocketHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            try {
                // 服务器异步创建绑定
                ChannelFuture f = b.bind(port).sync();
                channel = f.channel();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                System.out.println("NettyWebSocketServer error:" + e.getMessage());
            }
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            channel.closeFuture().syncUninterruptibly();
            System.out.println("NettyWebSocketServer Stop:" + port);
        }
    }

    public static void main(String[] args)  {
        System.out.println("NettyWebSocketServer start!");
        new NettyWebSocketServer(port).run();
    }
}
