package com.hisen.javaGaiShu.page91test20;
/**
 * 静态代码块相关
 * @author hisenyuan
 * 2017年2月27日    下午9:33:04
 */
public class JingTaiDaiMaKuai {
	private static String a;
	private String b;
	static {
		JingTaiDaiMaKuai.a = "我学习了很多语言";
		System.out.println(a);
		JingTaiDaiMaKuai t = new JingTaiDaiMaKuai();
		t.fina();
		t.b="Java语言";
		System.out.println(t.b);
	}

	static {
		JingTaiDaiMaKuai.a = "I Love Java";
		System.out.println(a);
	}

	public static void main(String[] args) {

	}

	static {
		JingTaiDaiMaKuai.a = "我还将继续学习下去";
		System.out.println(a);
	}

	private void fina() {
		System.out.println("但是我最喜欢的是：");
	}
}
