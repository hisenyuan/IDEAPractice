package com.hisen.jars.redisson;

import com.hisen.utils.JedisUtil;
import org.junit.Test;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;

/**
 * @author : hisenyuan
 * @date : 2018/3/5 1:07
 * @descriptor :
 */
public class ResissonDistributedLockTest {
  private static Jedis jedis = JedisUtil.getJedis();
  private static RedissonClient client = RedissonUtil.getClient();

  @Test
  public void testThread(){
    jedis.set("yhx","0");
    System.out.println(jedis.get("yhx"));
    boolean locked = client.getLock("hisenyuan1").isLocked();
    System.out.println(locked);
    for (int i = 0; i < 100000; i++) {
      new MyThread(1,"t",client,jedis).start();
    }
    locked = client.getLock("hisenyuan1").isLocked();
    System.out.println(locked);
    System.out.println(jedis.get("yhx"));
  }
}
