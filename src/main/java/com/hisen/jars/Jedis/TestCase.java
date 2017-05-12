package com.hisen.jars.Jedis;

import com.hisen.utils.JedisUtil;
import java.util.List;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 参考：http://www.cnblogs.com/vinozly/p/5459498.html
 * Created by hisenyuan on 2017/5/12 at 16:51.
 */
public class TestCase {

  public static void main(String[] args) {

  }

  @Test
  public void hello() {
    Jedis jedis = JedisUtil.getJedis();
    try {
      //向key->name中放入了val->hisen
      jedis.set("name", "hisen");
      System.out.println(jedis.get("name"));//hisen
      //追加hisenyuan到name中已有的value之后
      jedis.append("name", "hisenyuan");
      System.out.println(jedis.get("name"));//hisenhisenyuan
      //直接覆盖原来的数据
      jedis.set("name", "hisenyuan");
      System.out.println(jedis.get("name"));//hisenyuan
      //删除key对应的数据
      jedis.del("name");
      System.out.println(jedis.get("name"));//null
      //批量插入两条记录key为name,age
      jedis.mset("name", "hisen", "age", "25");
      System.out.println(jedis.mget("name", "age"));//[hisen, 25]
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JedisUtil.getPool().returnResource(jedis);
    }
  }

  @Test
  public void testKey() {
    Jedis jedis = JedisUtil.getJedis();
    //清空数据
    System.out.println(jedis.flushDB());
    System.out.println(jedis.echo("foo"));
    //判断key是否存在
    jedis.set("key", "value");
    System.out.println(jedis.exists("key"));
  }

  @Test
  public void testString() {
    Jedis jedis = JedisUtil.getJedis();
    jedis.set("key", "Hello World!");
    System.out.println(jedis.get("key"));//Hello World!
    //清空数据
    System.out.println(jedis.flushDB());//OK
    //存储
    jedis.set("foo", "bar");
    System.out.println(jedis.get("foo"));//bar
    //若key不存在，则存储
    jedis.setnx("foo", "foo not exits");
    System.out.println(jedis.get("foo"));//bar
    //覆盖数据
    jedis.set("foo", "foo update");
    System.out.println(jedis.get("foo"));//foo update
    //追加数据
    jedis.append("foo", ",hello world");
    System.out.println(jedis.get("foo"));//foo update,hello world
    //设置key的有效期,并存储数据
    jedis.setex("foo", 2, "foo not exits");
    System.out.println(jedis.get("foo"));//foo not exits
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(jedis.get("foo"));//null
    //获取并且更新数据
    jedis.set("foo", "foo update");
    System.out.println(jedis.getSet("foo", "foo modify") + "| after modify：" + jedis
        .get("foo"));//foo update| after modify：foo modify
    // 截取value的值
    System.out.println(jedis.getrange("foo", 1, 3));//oo
    System.out.println(jedis.mset("mset1", "mvalue1", "mset2", "mvalue2",
        "mset3", "mvalue3", "mset4", "mvalue4"));//OK
    System.out.println(
        jedis.mget("mset1", "mset2", "mset3", "mset4"));//[mvalue1, mvalue2, mvalue3, mvalue4]
    System.out.println(jedis.del(new String[]{"foo", "foo1", "foo3"}));//1
  }

  @Test
  public void testList() {
    Jedis jedis = JedisUtil.getJedis();
    jedis.del("list");
    jedis.rpush("list", "hello how are you?");
    jedis.rpush("list", "Fine thanks. I'm having fun with redis.");
    jedis.rpush("list", "I should look into this NOSQL thing ASAP");
    //jedis.lrange是按范围取出 key,start,end(-1 代表all)
    List<String> list = jedis.lrange("list", 0, -1);
    for (String l:list){
      System.out.println(l);
    }
    //清空数据
    System.out.println("清除数据："+jedis.flushDB());
    //添加数据
    jedis.lpush("lists", "vector");
    jedis.lpush("lists", "ArrayList");
    jedis.lpush("lists", "LinkedList");
    // 数组长度
    System.out.println(jedis.llen("lists"));
    // 排序
//    System.out.println(jedis.sort("lists"));
    // 字串
    System.out.println(jedis.lrange("lists", 0, 3));
    // 修改列表中单个值
    jedis.lset("lists", 0, "hello list!");
    // 获取列表指定下标的值
    System.out.println(jedis.lindex("lists", 1));
    // 删除列表指定下标的值
    System.out.println(jedis.lrem("lists", 1, "vector"));
    // 删除区间以外的数据
    System.out.println(jedis.ltrim("lists", 0, 1));
    // 列表出栈
    System.out.println(jedis.lpop("lists"));
    // 整个列表值
    System.out.println(jedis.lrange("lists", 0, -1));
  }
}
