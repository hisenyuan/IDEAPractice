package com.hisen.jars.redisson;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hisen.utils.JedisUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author : hisenyuan
 * @date : 2018/3/5 1:07
 * @descriptor :
 */
public class ResissonDistributedLockTest {
  Logger LOGGER = LoggerFactory.getLogger(ResissonDistributedLockTest.class);

  private static Jedis jedis = JedisUtil.getJedis();
  private static RedissonClient client = RedissonUtil.getClient();

  @Test
  public void testThread(){
    int num = 1000;
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("unpay-send-pool-%d").build();

    CountDownLatch latch=new CountDownLatch(num);
    // 创建线程池
    ExecutorService executor = new ThreadPoolExecutor(
        10,
        10000,
        200L,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(5120),
        namedThreadFactory,
        new ThreadPoolExecutor.AbortPolicy());

    // 业务逻辑
    jedis.set("yhx","0");
    System.out.println(jedis.get("yhx"));
    boolean locked = client.getLock("hisenyuan1").isLocked();
    System.out.println(locked);
    for (int i = 0; i < num; i++) {
      executor.execute(new MyThread(1,"t",client,jedis,latch));
    }
    try {
      latch.await();
      LOGGER.info("已经停止所有的子线程");
      executor.shutdown();
      locked = client.getLock("hisenyuan1").isLocked();
      System.out.println(locked);
      System.out.println(jedis.get("yhx"));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
