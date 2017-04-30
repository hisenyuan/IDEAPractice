package com.hisen.interview;

import java.io.UnsupportedEncodingException;

public class GB2312__to__ISO_8859_1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s1="你好";
		String s2=new String(s1.getBytes("GB2312"),"ISO-8859-1");
		System.out.println(s2);
	}
}
