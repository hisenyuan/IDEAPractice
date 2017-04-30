package com.hisen.tieba;

import java.util.ArrayList;
import java.util.List;

public class PostBarTest {
	public static void main(String[] args) {
		BaiduPostBar bar1 = new BaiduPostBar(1, "讨论java的兴衰", "依然世界第一", "2017-1-11");
		BaiduPostBar bar2 = new BaiduPostBar(2, "java学习指导", "这样快速学习java", "2017-1-12");
		BaiduPostBar bar3 = new BaiduPostBar(3, "java版本的问题", "应该怎么样选择", "2017-1-13");
		
		//添加帖子到 帖子集合BaiduPostBar1
		BaiduPostBar1.barList.add(bar1);
		BaiduPostBar1.barList.add(bar2);
		BaiduPostBar1.barList.add(bar3);
		
		//打印所有主题贴
		new BaiduPostBar1().selectBar();
		
		
		Thread th1 = new Thread(1,"新人学习java指导", "通过阅读书籍", "2017-1-13", bar2);
		Thread th2 = new Thread(2,"新人学习java指导", "通过观看教程", "2017-1-13", bar2);
		
		List<Thread> td = new ArrayList<Thread>();
		//添加回帖
		td.add(th1);
		td.add(th2);
		
		//放入map集合
		Thread1.map.put(bar2.getNum(), td);
		
		System.out.println("\t 主题为2的跟帖有：");
		
		for (int i = 0; i < new Thread1().selectThread(bar2).size(); i++) {
			System.out.println(new Thread1().selectThread(bar2).get(i));
		}
		
	}
}
