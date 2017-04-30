package com.hisen.split;

public class SplitLength {
	public static void main(String[] args) {
		String s = "5||1||||||||||||";
		int i = s.split("\\|",-1).length;
		System.out.println(i);//15
	}
}
