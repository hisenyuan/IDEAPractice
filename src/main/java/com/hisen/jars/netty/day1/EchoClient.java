package com.hisen.jars.netty.day1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author hisenyuan
 * @time 2017/12/27 15:48
 * @description
 */
public class EchoClient {
  private final String host;
  private final int port;

  public EchoClient(String host, int port) {
    this.host = host;
    this.port = port;
  }
  public void start() throws InterruptedException {
    NioEventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .remoteAddress(host,port)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new EchoClientHandler());
            }
          });
      ChannelFuture future = bootstrap.connect().sync();
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      group.shutdownGracefully().sync();
    }
  }

  public static void main(String[] args) {
    String host = "127.0.0.1";
    int port = 8090;
    try {
      new EchoClient(host,port).start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
