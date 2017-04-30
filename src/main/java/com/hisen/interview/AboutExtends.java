package com.hisen.interview;

/**
 * 变量不能被重写
 * 
 * @author hisenyuan 2017年1月18日 下午10:33:33
 */
public class AboutExtends {
	public static class A {
		public int a = 0;

		public void fun() {
			System.out.println("A");
		}

		static {
			System.out.println("Astatic");
		}
		{
			System.out.println("I'm A class");
		}
	}

	public static class B extends A {
		public int a = 1;

		public void fun() {
			System.out.println("B");
		}

		static {
			System.out.println("Bstatic");
		}
		{
			System.out.println("I'm B class");
		}
	}

	public static void main(String[] args) {
		// 里面的static块方法，new了就会执行
		// new new B()两个都执行，new new A()执行A的
		// static代码块在{}代码块后面执行
		A classA = new B();
		System.out.println(classA.a);
		classA.fun();
		// 输出信息
		// Astatic
		// Bstatic
		// I'm A class
		// I'm B class
		// 1
		// B
		
		// 多态记忆口诀
		// 变量多态看左边
		// 方法多态看右边
		// 静态多态看左边
	}
}
