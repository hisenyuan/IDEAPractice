package com.hisen.algorithms;

/**
 * @Author hisenyuan
 * @Description 全排列
 * @Date 2019/4/7 22:02
 */
public class Permutations {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        fullPermutation(arr, 0, arr.length - 1);
    }

    private static void fullPermutation(int[] data, int start, int end) {
        if (start == end) {
            final StringBuilder sb = new StringBuilder();
            for (int i : data) {
                sb.append(i);
            }
            System.out.println(sb.toString());
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(data, i, start);
            fullPermutation(data, start + 1, end);
            swap(data, i, start);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
