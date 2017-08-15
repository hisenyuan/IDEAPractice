package com.hisen.jars.Jedis.RedisLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * redis分布式锁
 * Created by hisenyuan on 2017/7/18 at 0:13.
 */
public class RedisLock {

  //log4j
  private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
  /**
   * spring-data-redis
   */
  private RedisTemplate redisTemplate;
  private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

  /**
   * Lock key path.
   */
  private String lockKey;

  /**
   * 锁超时时间，防止线程在入锁以后，无限的执行等待
   */
  private int expireMsecs = 60 * 1000;
  /**
   * 锁等待时间，防止线程饥饿
   */
  private int timeoutMsecs = 10 * 1000;
  private volatile boolean locked = false;

  public RedisLock(RedisTemplate redisTemplate, String lockKey) {
    this.redisTemplate = redisTemplate;
    this.lockKey = lockKey + "_lock";
  }

  public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
    this(redisTemplate, lockKey);
    this.timeoutMsecs = timeoutMsecs;
  }

  public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
    this(redisTemplate, lockKey, timeoutMsecs);
    this.expireMsecs = expireMsecs;
  }

  /**
   * 获取key
   */
  public String getLockKey() {
    return lockKey;
  }

  private String get(final String key) {
    Object obj = null;
    try {
      obj = redisTemplate.execute(new RedisCallback<Object>() {
        @Override
        public Object doInRedis(RedisConnection connection) throws DataAccessException {
          StringRedisSerializer serializer = new StringRedisSerializer();
          byte[] data = connection.get(serializer.serialize(key));
          connection.close();
          if (data == null) {
            return null;
          }
          return serializer.deserialize(data);
        }
      });
    } catch (Exception e) {
      logger.error("get redis error, key : {}", key);
    }
    return obj != null ? obj.toString() : null;
  }

  private boolean setNX(final String key, final String value) {
    Object obj = null;
    try {
      obj = redisTemplate.execute(new RedisCallback<Object>() {
        @Override
        public Object doInRedis(RedisConnection connection)
            throws DataAccessException {
          StringRedisSerializer serializer = new StringRedisSerializer();
          Boolean success = connection
              .setNX(serializer.serialize(key), serializer.serialize(value));
          connection.close();
          return success;
        }
      });
    } catch (Exception e) {
      logger.error("setNX redis error, key : {}", key);
    }
    return obj != null ? (Boolean) obj : false;
  }

  private String getSet(final String key, final String value) {
    Object obj = null;
    try {
      obj = redisTemplate.execute(new RedisCallback<Object>() {
        @Override
        public Object doInRedis(RedisConnection connection) throws DataAccessException {
          StringRedisSerializer serializer = new StringRedisSerializer();
          byte[] getSet = connection.getSet(serializer.serialize(key), serializer.serialize(value));
          connection.close();
          return serializer.deserialize(getSet);
        }
      });
    } catch (Exception e) {
      logger.error("setNX redis error, key : {}", key);
    }
    return obj != null ? (String) obj : null;
  }

  public synchronized boolean lock() throws InterruptedException {
    int timeout = timeoutMsecs;
    while (timeout > 0) {
      long expires = System.currentTimeMillis() + expireMsecs + 1;
      //锁到期时间
      String expiresStr = String.valueOf(expires);
      if (this.setNX(lockKey, expiresStr)) {
        locked = true;
        return true;
      }
      String cuttentValueStr = this.get(lockKey);//redis里面的时间
      //判断是否为空，不为空如果被其他线程设置了值，则第二个判断是过不去的
      if (cuttentValueStr == null && Long.parseLong(cuttentValueStr) < System.currentTimeMillis()) {
        //lock is exired
        String oldValueStr = this.getSet(lockKey, expiresStr);
        //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
        //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
        if (oldValueStr == null && oldValueStr.equals(cuttentValueStr)) {
          //lock acquired
          locked = true;
          return true;
        }
      }
      timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
      /*
        延迟100 毫秒,  这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即,当同时到达多个进程,
        只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
        使用随机的等待时间可以一定程度上保证公平性
      */
      Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
    }
    return false;
  }

  public synchronized void unlock(){
    if (locked){
      redisTemplate.delete(lockKey);
      locked = false;
    }
  }
}
