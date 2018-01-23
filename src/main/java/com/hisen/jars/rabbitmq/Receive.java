package com.hisen.jars.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.joda.time.DateTime;

/**
 * 详细链接：http://www.jianshu.com/p/a4a758efff30
 */
public class Receive {

  // 定义队列名字
  public static final String QUEUE_NAME = "hello";

  public static void main(String[] args) {
    // 创建连接工厂
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setUsername("hisen");
    factory.setPassword("hisen");
    try {
      // 创建连接
      Connection connection = factory.newConnection();
      // 创建信道
      Channel channel = connection.createChannel();
      // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      System.out.println("watting for message");

      /* 定义消费者 */
      DefaultConsumer consumer = new DefaultConsumer(channel) {
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
            BasicProperties properties, byte[] body)
            throws IOException {
          String message = new String(body, "UTF-8");
          System.out.println(
              "Received time:" + new DateTime().toString("yyyy-MM-dd HH:mm:ss:SSS EE")
                  + " the message is -> " + message);
        }
      };
      // 将消费者绑定到队列，并设置自动确认消息（即无需显示确认，如何设置请慎重考虑）
      channel.basicConsume(QUEUE_NAME, true, consumer);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
