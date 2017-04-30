package com.hisen.javanet;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxTest {
	public static void main(String[] args) {

		// 按指定模式在字符串查找
		String line = "magnet:?xt=urn:btih:bd51204cfd4c815727cefff3f2633f12ec7ad9cd&amp;tr=http://bt.mp4ba.com:2710/announce";
		String pattern = "(magnet:\\?xt=).*";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		while(m.find()) {
			System.out.println("Found value: " + m.group(0));
		} 
	}
}