package com.hisen.jars.jmx;

import java.lang.management.ManagementFactory;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * 主方法，运行后在Jconsole里面可实现操作
 * 参考：http://www.cnblogs.com/dongguacai/p/5900507.html
 * Created by hisenyuan on 2017/5/15 at 20:05.
 */
public class HelloAgent {

  public static void main(String[] args) throws JMException, Exception {
    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    ObjectName helloName = new ObjectName("jmxBean:name=hello");
    //create mbean and register mbean
    server.registerMBean(new Hello(), helloName);
    Thread.sleep(60 * 60 * 1000);
  }
}
