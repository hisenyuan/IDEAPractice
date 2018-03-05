package com.hisen.jars.redisson;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;

/**
 * @author : hisenyuan
 * @date : 2018/3/5 0:40
 * @descriptor :
 */
public class MyThread extends Thread{
  private int num;
  private String print;
  private RedissonClient client;
  private Jedis jedis;
  public MyThread(int num,String print,RedissonClient client,Jedis jedis) {
    this.num = num;
    this.print = print;
    this.client = client;
    this.jedis =jedis;
  }

  @Override
  public void run() {
    try {
      RLock lock = client.getLock("hisenyuan1");
      if (lock.tryLock(10,1, TimeUnit.SECONDS)) {
        jedis.incrBy("yhx", num);
        System.out.println(print+this.getName());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
