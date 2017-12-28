package com.hisen.jars.netty.day1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author hisenyuan
 * @time 2017/12/27 15:38
 * @description
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in)
      throws Exception {
    // 记录已接收信息的转储
    System.out.println("Client recevied : " + in.toString(CharsetUtil.UTF_8));
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    // 当被通知channel是活跃的时候，发送一条消息
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!",CharsetUtil.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 发生异常，记录并且关闭
    cause.printStackTrace();
    ctx.close();
  }
}
