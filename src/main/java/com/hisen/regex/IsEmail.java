package com.hisen.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsEmail {
	public static void main(String[] args) {
		String email = "hisenyuan@163.com";
		String patternstr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern pattern = Pattern.compile(patternstr);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			System.out.println("邮箱地址正确");
		}else{
			System.out.println("邮箱地址错误");
		}
		
	}
}
