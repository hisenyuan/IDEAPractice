package com.hisen.utils;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis工具类
 * Created by hisenyuan on 2017/5/12 at 16:58.
 */
public class JedisUtil {
  private static String ip = "127.0.0.1";
  private static int port = 6379;


  /**
   * 私有构造器
   */
  public JedisUtil() {
  }

  private static Map<String, JedisPool> maps = new HashMap<>();

  /**
   * 获取连接池
   *
   * @return 连接池实例
   */
  public static JedisPool getPool() {
    String key = ip + ":" + port;
    JedisPool pool = null;
    if (!maps.containsKey(key)) {
      JedisPoolConfig config = new JedisPoolConfig();
      //此处可以封装
      config.setMaxIdle(10);
      config.setMaxWaitMillis(1000);
      pool = new JedisPool(config, ip, port, 3000);
      maps.put(key, pool);
    } else {
      pool = maps.get(key);
    }
    return pool;
  }

  /**
   * 类级内部类，与外部类的实例无绑定关系，实现了延迟加载
   */
  private static class RedisUtilHolder {

    //静态初始化器，由JVM来保证线程安全
    private static JedisUtil instance = new JedisUtil();
  }

  /**
   * 当getInstance方法第一次被调用的时候，它第一次读取
   * RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
   * 态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
   * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本* @return
   */
  public static JedisUtil getInstance() {
    return RedisUtilHolder.instance;
  }

  /**
   * 获取Redis实例.
   *
   * @return Redis工具类实例
   */
  public static Jedis getJedis() {
    Jedis jedis = null;
    int count = 0;
    do {
      try {
        jedis = getPool().getResource();
      } catch (Exception e) {
        Logger log = LoggerFactory.getLogger(JedisUtil.class);
        log.error("get redis master1 failed!", e);
        // 销毁对象
        getPool().returnBrokenResource(jedis);
      }
      count++;
    } while (jedis == null && count < 10);//10可以封装到一个对象
    return jedis;
  }

  /**
   * 释放redis实例到连接池.
   *
   * @param jedis redis实例
   */
  public void closeJedis(Jedis jedis, String ip, int port) {
    if (jedis == null) {
      getPool().returnResource(jedis);
    }
  }
}
