package com.hisen.jars.netty.day1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 * @author hisenyuan
 * @time 2017/12/27 15:14
 * @description netty服务器端
 */
public class EchoServer {

  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) {
    int port = 8090;
    // 调用服务器的start方法
    try {
      new EchoServer(port).start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void start() throws InterruptedException {
    final EchoServerHandler handler = new EchoServerHandler();
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      // 指定group来接受或者处理新的连接
      bootstrap.group(group)
          // 指定传输所使用的channel
          .channel(NioServerSocketChannel.class)
          // 设置套接字端口
          .localAddress(new InetSocketAddress(port))
          // 添加一个EchoServerHandler到子channel的ChannelPipeline
          .childHandler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
              // 因为进行了Sharable标注，所以每次都可以使用相同的handler
              ch.pipeline().addLast(handler);
            }
          });
      // 异步绑定服务器，调用sync()方法(阻塞)等待直到绑定完成
      ChannelFuture f = bootstrap.bind().sync();
      // 获取channel的closeFuture，并且阻塞当前线程，直到他完成
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
        // 关闭EventLoopGroup,释放所有的资源
        group.shutdownGracefully().sync();
    }
  }
}
