package com.hisen.jars.Jedis;

import com.hisen.utils.JedisUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 使用Pipeline在对Redis批量读写的时候，性能上有非常大的提升。
 * 但是数据量大的话消耗性能，毕竟是在内存中
 * Created by hisenyuan on 2017/5/19 at 15:29.
 */
public class PipeliningTest {

  @Test
  public void pipleline() {
    Jedis jedis = JedisUtil.getJedis();
    Map<String, String> data = new HashMap<>();
    jedis.select(8);
    jedis.flushDB();
    long start = System.currentTimeMillis();
    //直接使用hmset
    for (int i = 0; i < 10000; i++) {
      data.clear();
      data.put("k_" + i, "v_" + i);
      jedis.hmset("key_" + i, data);
    }
    long end = System.currentTimeMillis();
    System.out.println("DBSize:[ " + jedis.dbSize() + " ]...");
    System.out.println("hmset without pipeline used [ " + (end - start) + " ] millisecond ..");

    jedis.select(8);
    jedis.flushDB();
    //使用pipeline hmset
    Pipeline pipeline = jedis.pipelined();
    long pipelineStart = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      data.clear();
      data.put("k_" + i, "v_" + i);
      pipeline.hmset("key_" + i, data);
    }
    pipeline.sync();
    long pipelineEnd = System.currentTimeMillis();
    System.out.println("DBsize:[ " + jedis.dbSize() + " ] .. ");
    System.out.println(
        "hmset with pipeline used [ " + (pipelineEnd - pipelineStart) + " ] millisecond ..");

    //hmget
    Set keys = jedis.keys("*");
    //直接使用Jedis hgetall
    long hmgetStart = System.currentTimeMillis();
    Map<String, Map<String, String>> stringMapMap = new HashMap<>();
    for (Object key : keys) {
      stringMapMap.put((String) key, jedis.hgetAll((String) key));
    }
    long hmgetEnd = System.currentTimeMillis();
    System.out.println("result size:[" + stringMapMap.size() + "] ..");
    System.out.println(
        "hgetAll without pipeline used [ " + (hmgetEnd - hmgetStart) + " ] millisecond ..");

    //使用pipeline hgetall
    Map<String, Response<Map<String, String>>> responses = new HashMap<String, Response<Map<String, String>>>(
        keys.size());
    stringMapMap.clear();
    long pipelineHgetAllStart = System.currentTimeMillis();
    for (Object key : keys) {
      responses.put((String) key, pipeline.hgetAll((String) key));
    }
    pipeline.sync();
    for (String k : responses.keySet()) {
      stringMapMap.put(k, responses.get(k).get());
    }
    long pipelineHgetAllEnd = System.currentTimeMillis();
    System.out.println("result size:[ " + stringMapMap.size() + " ] ..");
    System.out.println("hgetAll with pipeline used [" + (pipelineHgetAllEnd - pipelineHgetAllStart)
        + "] millisecond ..");
  }
  /**
   * 测试结果如下，使用pipeline的速度不错
   * DBSize:[ 10000 ]...
   * hmset without pipeline used [ 5495 ] millisecond ..
   * DBsize:[ 10000 ] ..
   * hmset with pipeline used [ 164 ] millisecond ..
   * result size:[10000] ..
   * hgetAll without pipeline used [ 3329 ] millisecond ..
   * result size:[ 10000 ] ..
   * hgetAll with pipeline used [63] millisecond ..
   */
}
