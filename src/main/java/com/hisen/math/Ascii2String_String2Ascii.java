package com.hisen.math;

import java.io.UnsupportedEncodingException;
/**
 * ASCII/字符串 互相转换
 * @author hisenyuan
 * 2016年3月28日    下午6:05:28
 */
public class Ascii2String_String2Ascii {
	public static void main(String[] args) throws UnsupportedEncodingException {   
		Ascii2String();// ASCII转换为字符串 
		String2Ascii();// 字符串转换为ASCII码   
    }   
    public static void Ascii2String() {// ASCII转换为字符串   
        String s = "22307,35806,24555,20048";// ASCII码   
        String[] chars = s.split(",");   
        System.out.println("ASCII -> 汉字 \n----------------------");   
        for (int i = 0; i < chars.length; i++) {   
            System.out.println(chars[i] + " -> " + (char) Integer.parseInt(chars[i]));   
        }   
    }   
  
    public static void String2Ascii() {// 字符串转换为ASCII码   
        String s = "新年快乐！";// 字符串   
        char[] chars = s.toCharArray(); // 把字符中转换为字符数组 
        System.out.println("\n\n汉字 -> ASCII\n----------------------");   
        for (int i = 0; i < chars.length; i++) {// 输出结果   
            System.out.println(" " + chars[i] + " -> " + (int) chars[i]);   
        }   
    }   
}
