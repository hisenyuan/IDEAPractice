package com.hisen.jars.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 详细链接：http://www.jianshu.com/p/a4a758efff30
 */
public class Send {

  // 定义队列名字
  public static final String QUEUE_NAME = "hello";

  public static void main(String[] args) throws IOException, TimeoutException {
    // 创建连接工厂
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setPort(5672);
    factory.setUsername("hisen");
    factory.setPassword("hisen");
    Connection connection = null;
    Channel channel = null;
    try {
      // 创建连接
      connection = factory.newConnection();
      // 创建信道
      channel = connection.createChannel();
      // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      for (int i = 0; i < 10; i++) {
        // 定义消息内容
        String message = "Hello World - " + i;
        // 通过信道发布内容
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("send : " + message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    } finally {
      if (null != channel) {
        channel.close();
      }
      if (null != connection) {
        connection.close();
      }
    }
  }
}
