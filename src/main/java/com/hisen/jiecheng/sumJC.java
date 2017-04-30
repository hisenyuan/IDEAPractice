package com.hisen.jiecheng;
/**
 * 求 1!+2!+3! ....+n!
 * @author Administrator
 *
 */
public class sumJC {
public static void main(String[] args) {
	int result = 0;
	for (int i = 1; i <=4; i++) {
		int a =1;
		for (int j = 1; j <= i; j++) {
			a=a*j;
			System.out.println(a+"*"+j+"="+a*j);
		}
		System.out.println("-----------------");//
		result +=a;//把a都加起来，而不是把a*j的结果加起来
	}
	System.out.println(result);
}
}