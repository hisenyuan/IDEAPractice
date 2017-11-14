package com.hisen.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : yhx
 * @date : 2017/11/14 23:36
 * @descriptor : 测试不同的HashMap - 使用ExecutorService来并发运行5个线程，每个线程添加/获取500K个元素。
 */
public class CrunchifyConcurrentHashMapVsSynchronizedMap {
  private static final int THREAD_POOL_SIZE = 5;
  public static Map<String, Integer> crunchifyHashTableObject = null;
  public static Map<String, Integer> crunchifySynchronizedMapObject = null;
  public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;

  public static void main(String[] args) throws InterruptedException {
    // Test with Hashtable Object
    crunchifyHashTableObject = new Hashtable<>();
    crunchifyPerformTest(crunchifyHashTableObject);

    // Test with synchronizedMap Object
    crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
    crunchifyPerformTest(crunchifySynchronizedMapObject);

    // Test with ConcurrentHashMap Object
    crunchifyConcurrentHashMapObject = new ConcurrentHashMap<>();
    crunchifyPerformTest(crunchifyConcurrentHashMapObject);

    /**
     * 测试结果
     Test start for:class java.util.Hashtable
     2500K entried added/retrieved in 2953 ms
     2500K entried added/retrieved in 4649 ms
     2500K entried added/retrieved in 2736 ms
     2500K entried added/retrieved in 2628 ms
     2500K entried added/retrieved in 2621 ms
     For class java.util.Hashtable the average time is 3117 ms

     Test start for:class java.util.Collections$SynchronizedMap
     2500K entried added/retrieved in 3036 ms
     2500K entried added/retrieved in 2881 ms
     2500K entried added/retrieved in 2692 ms
     2500K entried added/retrieved in 3020 ms
     2500K entried added/retrieved in 2806 ms
     For class java.util.Collections$SynchronizedMap the average time is 2887 ms

     Test start for:class java.util.concurrent.ConcurrentHashMap
     2500K entried added/retrieved in 4378 ms
     2500K entried added/retrieved in 1126 ms
     2500K entried added/retrieved in 1008 ms
     2500K entried added/retrieved in 935 ms
     2500K entried added/retrieved in 1069 ms
     For class java.util.concurrent.ConcurrentHashMap the average time is 1703 ms
     */
  }

  private static void crunchifyPerformTest(Map<String, Integer> crunchifyThreads)
      throws InterruptedException {
    System.out.println("Test start for:" + crunchifyThreads.getClass());
    long avgTime = 0;
    for (int i = 0; i < 5; i++) {
      long startTime = System.nanoTime();

      ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
      for (int j = 0; j < THREAD_POOL_SIZE; j++) {
        executorService.execute(new Runnable() {
          @Override
          public void run() {
            for (int k = 0; k < 500000; k++) {
              Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
              // Retrieve value. We are not using it anywhere
              Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));
              // Put value
              crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
            }
          }
        });
      }
      // Make sure executor stops
      executorService.shutdown();
      // Blocks until all tasks have completed execution after a shutdown request
      executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
      long entTime = System.nanoTime();
      long totalTime = (entTime - startTime) / 1000000L;
      avgTime += totalTime;
      System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
    }
    System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + avgTime / 5 + " ms\n");
  }
}
