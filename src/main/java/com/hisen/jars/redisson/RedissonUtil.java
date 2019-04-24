package com.hisen.jars.redisson;

import com.hisen.utils.JedisUtil;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;

/**
 * @author : hisenyuan
 * @date : 2018/3/4 22:13
 * @descriptor :
 */
public class RedissonUtil {
  private static Jedis jedis = JedisUtil.getJedis();
  private static RedissonClient client = null;

  private static final String url ="127.0.0.1:6379";

  public static RedissonClient getClient() {
    if (client == null){
      final RList<String> hisen = client.getList("hisen");
      hisen.add("1");
      Config config = new Config();
      config.useSingleServer().setAddress(url);
      client = Redisson.create(config);
    }
    return client;
  }
}
