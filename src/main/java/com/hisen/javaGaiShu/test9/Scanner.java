package com.hisen.javaGaiShu.test9;
/**
 * 文档注释
 * 乘法表
 * @author hisenyuan
 * 2017年2月10日    下午9:44:18
 */
public class Scanner {
	public static void main(String[] args) {
		System.out.print("乘法口诀表：");
		for (int i = 0; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j+"*"+i+"="+j*i+"\t");
			}
			System.out.println();
		}
	}
}
