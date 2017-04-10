package com.hisen.Jredis;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by hisen on 17-4-11.
 * 有待参考：http://www.cnblogs.com/vinozly/p/5459498.html
 */
public class FirstTest {

  @Test
  public static void testClient() {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    jedis.select(2); //切换Redis数据库
    jedis.set("firstJedis", "hello,Jedis"); //与Redis命令行操作基本一致
    System.out.println(jedis.get("firstJedis"));
  }

  @Test
  public void testMap() {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    jedis.select(2);
    jedis.hset("family", "lbq", "65"); //同Redis命令行中的hset操作，如名为family的set不存在，则创建set并放入名为lbq的元素，值为65
    jedis.hset("family", "zjz", "62"); //Redis不支持int类型，如不传String则会报错。
    System.out.println(jedis.hmget("family", "lbq", "zjz"));

    Map testMap1 = new HashMap();
    testMap1.put("num1", "1"); //此处同上面，不能传非String型
    testMap1.put("num2", "15");
    testMap1.put("num3", "606");
    testMap1.put("num4", "1024");
    jedis.hmset("testMap1", testMap1); //传入整个map对象入redis
    System.out.println(jedis.hmget("testMap1", "num1", "num2", "num3", "num4"));
  }
}
