package com.hisen.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArrayListTest {
	public static void main(String[] args) {
		List<Integer> al=new ArrayList<Integer>();
		al.add(1);
		al.add(5);
		al.add(10);
		al.add(100);
		//移除下标为0的元素
		al.remove(0);
		//设置下标为1的元素值为99
		al.set(1, 99);
		
		int size = al.size();
		Boolean flag = al.isEmpty();
		int a = al.get(0);
		
		ListIterator<Integer> li = al.listIterator();
		while(li.hasNext()){
			System.out.println(li.next());
		}
		System.out.println("al的大小："+size+";al为空："+flag+";al的第一个元素:"+a);
	}
}
