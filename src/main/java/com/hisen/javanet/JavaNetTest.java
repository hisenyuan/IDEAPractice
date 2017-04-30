package com.hisen.javanet;

import java.io.IOException;
import java.net.InetAddress;
/**
 * 参考：http://www.cnblogs.com/oubo/archive/2012/01/16/2394641.html
 * @author hisenyuan
 * 2016年4月7日    下午3:40:58
 */
public class JavaNetTest {
	public static void main(String[] args)  {

	}
	/**
	 * Java中的InetAddress是一个代表IP地址的封装。
	 * IP地址可以由字节数组和字符串来分别表示，
	 * InetAddress将IP地址以对象的形式进行封装，
	 * 可以更方便的操作和获取其属性。
	 * InetAddress没有构造方法，
	 * 可以通过两个静态方法获得它的对象。
	 */
	public static void InetAddressTest() throws IOException{
		// 根据主机名来获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName("www.baidu.com");
		// 判断是否可以访问
		System.out.println("百度是否可以访问：" + ip.isReachable(2000));
		// 获取InetAddress实例的ip字符串
		System.out.println(ip.getHostAddress());
		// 根据原始ip地址（字节数组）来获取对应的InetAddress实例
		InetAddress local = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
		System.out.println("本机是否可以访问："+local.isReachable(2000));
		//获取InetAddress实例对应的全限定域名
		System.out.println(local.getCanonicalHostName());
	}
	/**
	 * 创建URL对象，并通过调用openConnection方法获得URLConnection对象；
	 * 设置URLConnection参数和普通请求属性；
	 * 向远程资源发送请求；
	 * 远程资源变为可用，程序可以访问远程资源的头字段和通过输入流来读取远程资源返回的信息。
	 */
	public static void URLTest(){
		
	}
}
