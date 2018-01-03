package com.hisen.jars.zookeeper;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author hisenyuan
 * @time 2017/12/26 17:41
 * @description zookeeper相关操作
 */
public class ZooKeeperTest implements Watcher {

  //超时时间
  private static final int SESSION_TIMEOUT = 5000;

  private ZooKeeper zk;
  private CountDownLatch connectedSignal = new CountDownLatch(1);

  /**
   * 连接zookeeper
   */
  public void connect(String host) throws Exception {
    zk = new ZooKeeper(host, SESSION_TIMEOUT, this);
    connectedSignal.await();
  }

  /**
   * 关闭zk连接
   */
  public void closeZk() throws Exception {
    zk.close();
  }


  @Override
  public void process(WatchedEvent watchedEvent) {
    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
      connectedSignal.countDown();
    }
  }


  /**
   * 创建zk节点
   */
  public void createZNode(String zNode) throws Exception {
    String path = "/" + zNode;
    // 创建没有数据的节点
    String createPath =
        zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    System.out.println(createPath);
  }

  /**
   * 设置节点的数据
   *
   * @param path 节点的路径
   * @param data 节点的数据
   */
  public void setData(String path, String data) {
    try {
      Stat stat = zk.exists(path, false);
      Stat setData = zk.setData(path, data.getBytes(), stat.getVersion());
      System.out.println(setData.toString());
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取内容
   */
  public String getData(String path) {
    String value = null;
    try {
      Stat stat = zk.exists(path, false);
      // 同步获取
      byte[] data = zk.getData(path, true, stat);
      value = new String(data);
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return value;
  }

  /**
   * 删除节点
   *
   * @param path 节点的路径
   */
  public void deleteNode(String path) {
    try {
      Stat exists = zk.exists(path, false);
      zk.delete(path, exists.getVersion());
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    try {
      ZooKeeperTest create = new ZooKeeperTest();
      create.connect("192.168.1.174:2281");
      String path = "/cn/hisenyuan/test1";
      // 创建节点
//      create.createZNode("cn");
//      create.createZNode("cn/hisenyuan");
//      create.createZNode("cn/hisenyuan/test1");


      // 存放list到zk
      ArrayList<Integer> integers = new ArrayList<>();
      integers.add(1);
      integers.add(2);
      integers.add(3);
      integers.add(4);
      integers.add(5);
      String value = JSON.toJSONString(integers);
      System.out.println("存放在zk上的值：" + value);
      create.setData(path, value);

      // 获取节点的值，更改
      String data = create.getData(path);
      System.out.println("获取到zk上的值：" + data);
      ArrayList list = JSON.parseObject(data, ArrayList.class);
      list.remove(0);
      String value1 = JSON.toJSONString(list);
      System.out.println("存放在zk上的值：" + value1);
      create.setData(path, value1);

      // 查看节点的数据
      System.out.println(create.getData(path));
      create.closeZk();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
