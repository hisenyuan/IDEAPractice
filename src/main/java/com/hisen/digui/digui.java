package com.hisen.digui;

public class digui {
public static void main(String[] args) {
	System.out.println(jc(4));
}
public static long jc(long n){
	if(n==0){
		return 1;
	}else{
		return n*jc(n-1);
	}
}
}
