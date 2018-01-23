package com.hisen.jars.netty.day1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author hisenyuan
 * @time 2017/12/27 14:52
 * @description 一个服务器处理类 -  @Sharable 表示可以被多个channel安全的共享
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf in = (ByteBuf) msg;
    // 将消息记录到控制台
    System.out.println("server received:" + in.toString(CharsetUtil.UTF_8));
    // 将收到的消息 写回给 发送者，而不冲刷出站消息
    ctx.write(in);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    // 将消息冲刷致远程节点，并且关闭该channel
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
  }

  /**
   * 如果不捕获异常，那么至少在channel对应的channelpipeline下有一个捕获了
   * 因为异常会在channelpipeline之间传递
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 打印异常栈跟踪
    cause.printStackTrace();
    // 关闭该channel
    ctx.close();
  }
}
