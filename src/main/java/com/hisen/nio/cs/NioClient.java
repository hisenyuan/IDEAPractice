package com.hisen.nio.cs;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 客户端
 *
 * @author hisenyuan 2017年2月27日    下午10:26:44
 */
public class NioClient {

  private static final int BLOCK_SIZE = 4096;
  private static final InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("127.0.0.1", 8989);
  private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE);
  private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);

  public static void main(String[] args) {
    SocketChannel socketChannel;
    try {
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      Selector selector = Selector.open();
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
      socketChannel.connect(SERVER_ADDRESS);
      SocketChannel client;

      while (true) {
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey selectionKey = iterator.next();
          if (selectionKey.isConnectable()) {
            System.out.println("Client:already connected");
            client = (SocketChannel) selectionKey.channel();
            if (client.isConnectionPending()) {
              client.finishConnect();
              sendBuffer.clear();
              sendBuffer.put("hello nio server".getBytes());
              sendBuffer.flip();

              client.write(sendBuffer);
              client.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
              client = (SocketChannel) selectionKey.channel();
              receiveBuffer.clear();
              int count = client.read(receiveBuffer);
              if (count > 0) {
                String receiveMessage = new String(receiveBuffer.array(), 0, count);
                System.out.println("Client：接收到来自Server的消息，" + receiveMessage);
                client.register(selector, SelectionKey.OP_WRITE);
              }
            } else if (selectionKey.isWritable()) {
              sendBuffer.clear();
              client = (SocketChannel) selectionKey.channel();
              String sendText = "hello server,key...";
              sendBuffer.put(sendText.getBytes());
              System.out.println("Client:客户端向服务器端发送数据--：" + sendText);
              client.register(selector, SelectionKey.OP_READ);
            }
          }
          selectionKeys.clear();
          Thread.sleep(3000);
        }
      }
    } catch (Exception e) {
      //这里改成Exception，不然Thread.sleep(3000);会报错
      e.printStackTrace();
    }

  }
}
