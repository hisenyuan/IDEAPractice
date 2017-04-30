package com.hisen.aboutlist;

import java.util.Arrays;

/**
 * 数组简单的打印方法
 * 一维数组：Arrays.toString
 * 二维数组：Arrays.deepToString
 *
 * @author hisenyuan 2016年4月22日    下午6:29:31
 */
public class PrintArray {

  public static void main(String[] args) {
    String[] arrayStr = new String[]{"Java", "Node", "Python", "Ruby"};
    System.out.println(Arrays.toString(arrayStr));
    // Output :[Java, Node, Python, Ruby]

    int[] arrayInt = {1, 3, 5, 7, 9};
    System.out.println(Arrays.toString(arrayInt));
    // Output : [1, 3, 5, 7, 9]

    String[][] deepArrayStr = new String[][]{{"yiibai1", "yiibai2"}, {"yiibai3", "yiibai4"}};
    System.out.println(Arrays.deepToString(deepArrayStr));
    // Output : [[yiibai1, yiibai2], [yiibai3, yiibai4]

    int[][] deepArrayInt = new int[][]{{1, 3, 5, 7, 9}, {2, 4, 6, 8, 10}};
    System.out.println(Arrays.deepToString(deepArrayInt));
    // Output : [[1, 3, 5, 7, 9], [2, 4, 6, 8, 10]]

  }
}
