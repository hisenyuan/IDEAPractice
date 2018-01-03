package com.hisen.thread.count_click_by_redis;

/**
 * @author hisenyuan
 * @description 执行点击的线程
 */
public class CountClickByRedisThread extends Thread{

  private int id;
  public CountClickByRedisThread(int id) {
    this.id = id;
  }

  @Override
  public void run() {
    super.run();
    ClickRedis.click();
    int count = ClickRedis.getCount();
    System.out.println("task:" + id + "\t 执行完毕\t线程编号：" + this.getId() + "\t当前值：" + count);
  }
}
