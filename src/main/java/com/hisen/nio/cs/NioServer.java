package com.hisen.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * NIO服务端
 *
 * @author hisenyuan 2017年2月27日 下午9:36:34
 */
public class NioServer {

  private final int port = 8989;
  private final int BLOCK_SIZE = 4096;

  private Selector selector;

  private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);
  private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE);

  // 构造函数
  public NioServer() throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    ServerSocket serverSocket = serverSocketChannel.socket();
    serverSocket.bind(new InetSocketAddress(port));
    selector = selector.open();
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("Server:init successfuly.");
  }

  public static void main(String[] args) {
    NioServer nioServer;
    try {
      nioServer = new NioServer();
      nioServer.linstenr();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void linstenr() throws IOException {
    while (true) {
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      if (selectionKeys.isEmpty()) {
        continue;
      }
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        iterator.remove();
        handlerKey(selectionKey);
      }
    }
  }

  private void handlerKey(SelectionKey selectionKey) throws IOException {
    ServerSocketChannel server;
    SocketChannel client;
    if (selectionKey.isAcceptable()) {
      server = (ServerSocketChannel) selectionKey.channel();
      client = server.accept();
      client.configureBlocking(false);
      client.register(selector, SelectionKey.OP_READ);
    } else if (selectionKey.isReadable()) {
      client = (SocketChannel) selectionKey.channel();
      receiveBuffer.clear();
      int count = client.read(receiveBuffer);
      if (count > 0) {
        String receiveMessage = new String(receiveBuffer.array(), 0, count);
        System.out.println("Server:接受客户端的数据：" + receiveMessage);
        client.register(selector, SelectionKey.OP_WRITE);
      }
    } else if (selectionKey.isWritable()) {
      sendBuffer.clear();
      client = (SocketChannel) selectionKey.channel();
      String sendMessage = "Send form ...Hello..." + new Random().nextInt(100) + ".";
      sendBuffer.put(sendMessage.getBytes());
      sendBuffer.flip();
      client.write(sendBuffer);
      System.out.println("Server:服务器想客户端发送数据：" + sendMessage);
      client.register(selector, SelectionKey.OP_READ);
    }
  }
}
