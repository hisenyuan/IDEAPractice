package com.hisen.aboutlist;

import java.util.ArrayList;

public class list1 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		String[] a=new String[2];
		a[0]="a";
		a[1]="b";
		list.add(a);
		System.out.println(((String[]) list.get(0))[0]);
	}
}
