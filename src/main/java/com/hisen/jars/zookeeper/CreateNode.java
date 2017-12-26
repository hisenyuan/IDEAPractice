package com.hisen.jars.zookeeper;

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
 * @description
 */
public class CreateNode implements Watcher {

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
   * 删除节点
   * @param path 节点的路径
   */
  public void deleteNode(String path){
    try {
      Stat exists = zk.exists(path, false);
      zk.delete(path,exists.getVersion());
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    try {

      CreateNode create = new CreateNode();
      create.connect("192.168.1.174:2281");
      // 创建节点
//      create.createZNode("root");
//      create.createZNode("sms");
//      create.createZNode("reg");
      // 设置节点的数据
//      create.setData("/root/sms/reg","{\"all\":\"^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\\\d{8}$\",\"chinamobile\":\"^(1(3[4-9]|5[0124789]|7[28]|8[23478])\\\\d{8}$)|(170[356]\\\\d{7}$)|(147\\\\d{8}$)\",\"telcom\":\"^(18[019]\\\\d{8})|(17[37]\\\\d{8})|(133\\\\d{8})|(153\\\\d{8})|(170[01]\\\\d{7})|(1349\\\\d{7}$)\",\"unicom\":\"^(1(3[0-2]|5[56]|8[56])\\\\d{8}$)|17[56]\\\\d{8}$|((170[789]|1713|1715|1716|1717|1718|1719)\\\\d{7}$)|(145\\\\d{8}$)\"}");
//      create.setData("/root/sms/channel", "192.168.1.174");
      create.deleteNode("/apps/hisen");
      create.closeZk();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
