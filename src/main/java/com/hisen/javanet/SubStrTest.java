package com.hisen.javanet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubStrTest {
public static void main(String[] args) {
	String a= "<title>HiSEN - hisenyuan's blog</title>";
	int x=a.indexOf("<title>");
	int y=a.indexOf("</title>");
	String b=a.substring(x, y);
	String f=b.replace("<title>", "");
	System.out.println(f);
	
	String title=null;
	String pattern1 = "<title>(.*)</title>";
	// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern1);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(a);
		//循环获取目标链接
		while(m.find()) {
			//把获取到的链接装进去
			title = (m.group(0));
			System.out.println(m.group(0));
		} 
		System.out.println(title);
}
}
