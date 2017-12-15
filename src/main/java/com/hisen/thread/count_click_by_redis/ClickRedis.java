package com.hisen.thread.count_click_by_redis;

import com.hisen.utils.JedisUtil;
import redis.clients.jedis.JedisPool;
/**
 * @author hisenyuan
 * @description 操作redis的线程类
 */
public class ClickRedis {

  /**
   * 必须使用线程池，而且线程池要大于并发数，否则会出现redis超时
   */
  private static JedisPool jedis = JedisUtil.getPool();

  public static void click() {
    jedis.getResource().incrBy("hisen", 1);
  }

  public static int getCount() {
    return Integer.parseInt(jedis.getResource().get("hisen"));
  }

  public static void declare() {
    jedis.getResource().del("hisen");
    jedis.close();
  }
}
