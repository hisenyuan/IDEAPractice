package com.hisen.math;

/**
 * 利用ASCII生成置顶长度的密码
 * 密码包含大小写字母、数字、常用符号的密码
 * 
 * ASCII 33-126含有大小写字母、数字、常用符号
 * 
 * @author hisenyuan 2016年3月28日 下午6:13:14
 */
public class RandomCode {
	public static void main(String[] args) {
		System.out.print("为您生成的密码" + code(18));
		//hisen();//判断随机数的范围
	}

	/**
	 * 生成随机密码
	 * @param length 密码长度
	 * @return 生成的密码字符串
	 */
	public static String code(int length) {
		String s = "";
		for (int i = 0; i < length; i++) {
			//利用22~126的随机数，把ascii码转成字符拼接
			int random = 33 + (int) (Math.random() * 94);
			s += (char) Integer.parseInt(String.valueOf(random));
		}
		return s;
	}

	/**
	 * 判断随机数的范围
	 */
	public static void hisen() {
		Boolean flag = true;
		int i = 0;
		int f = 0;
		while (flag) {
			System.out.println(f++);
			i = 33 + (int) (Math.random() * 94);
			// 希望得到 33~126的数字
			if (i == 33) {
				System.out.println("====找到了");
				flag = false;
			}
		}
	}

}
