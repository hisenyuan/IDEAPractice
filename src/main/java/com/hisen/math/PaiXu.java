package com.hisen.math;

import java.util.Arrays;

/**
 * 把大于首项的数放在数组尾部,小于首项的放在数组的前端
 * { 2, 1, -7, -3, 5, 6, -1 } 变成 {-1, 1, -7, -3, 2, 6, 5}
 * @author hisenyuan
 * 2016年4月22日    下午6:02:55
 */
public class PaiXu {
	public static void main(String[] args) {
		int[] a = { 2, 1, -7, -3, 5, 6, -1 };
		int i = 0;
		int j = a.length - 1;
		int temp = a[i];
		while (i < j) {
			while (i < j && a[j] > temp)
				--j;
			if (i < j) {
				a[i] = a[j];
				++i;
			}

			while (i < j && a[i] < temp)
				++i;
			if (i < j) {
				a[j] = a[i];
				--j;
			}

		}
		a[i] = temp;
		System.out.println(Arrays.toString(a));
		//[-1, 1, -7, -3, 2, 6, 5]
	}
}
