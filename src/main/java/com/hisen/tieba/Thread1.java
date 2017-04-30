package com.hisen.tieba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thread1 {
	public static Map<Integer, List<Thread>> map = new HashMap<Integer, List<Thread>>();
	public List<Thread> selectThread(BaiduPostBar b){
		//遍历返回每个主题对象 BaiduPostBar
		for (Integer barnum : map.keySet()) {
			if (barnum == b.getNum()) {
				return map.get(barnum);
			}else{
				return null;
			}
			
		}
		return null;
	}
}
