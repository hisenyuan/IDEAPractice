package com.hisen.jars.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/2/1 10:33
 * @description
 */
public class ListAllNode {

  ArrayList<String> nodes = new ArrayList<>();

  @Test
  public void printZnodeTest() throws InterruptedException, IOException, KeeperException {
    List<String> znodes = printZnode("/");
    for (String node : znodes) {
      System.out.println(node);
    }
    Map<String, String> alldata = getAlldata(znodes);
    for (Entry<String, String> data : alldata.entrySet()) {
      System.out.println("节点:" + data.getKey() + "\n数据:" + data.getValue());
    }
  }

  private List<String> printZnode(String path)
      throws IOException, KeeperException, InterruptedException {
    if (path.startsWith("/root")) {
      nodes.add(path);
    }
    String conn = "192.168.1.174:2288";
    ZooKeeper zooKeeper = new ZooKeeper(conn, 50000, new Watcher() {
      @Override
      public void process(WatchedEvent watchedEvent) {
        System.out.println("over : " + watchedEvent);
      }
    });
    List<String> children = zooKeeper.getChildren(path, false);
    if (path.equals("/")) {
      path = "";
    }
    for (String node : children) {
      printZnode(path + "/" + node);
    }
    return nodes;
  }

  private Map<String, String> getAlldata(List<String> znodes)
      throws IOException, KeeperException, InterruptedException {
    Map<String, String> nodeValues = new HashMap<>();
    String conn = "192.168.1.174:2288";
    ZooKeeper zk = new ZooKeeper(conn, 50000, new Watcher() {
      @Override
      public void process(WatchedEvent watchedEvent) {
        System.out.println("over : " + watchedEvent);
      }
    });
    for (String node : znodes) {
      Stat stat = zk.exists(node, false);
      // 同步获取
      byte[] data = zk.getData(node, true, stat);
      String value = data==null? "" : new String(data);
      nodeValues.put(node, value);
    }
    return nodeValues;
  }

}
