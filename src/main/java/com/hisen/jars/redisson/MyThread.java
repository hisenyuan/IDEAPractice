package com.hisen.jars.redisson;

import java.util.concurrent.CountDownLatch;
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
  private CountDownLatch latch;

  public MyThread(int num,String print,RedissonClient client,Jedis jedis,CountDownLatch latch) {
    this.num = num;
    this.print = print;
    this.client = client;
    this.jedis =jedis;
    this.latch = latch;
  }

  @Override
  public void run() {
    RLock lock = client.getLock("hisenyuan1");
    try {
      if (lock.tryLock(10,2, TimeUnit.SECONDS)) {
        jedis.incrBy("yhx", num);
        System.out.println(print+this.getName());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
    latch.countDown();
  }
}
