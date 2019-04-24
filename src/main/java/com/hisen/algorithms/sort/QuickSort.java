package com.hisen.algorithms.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : yhx
 * @date : 2017/11/26 22:29
 * @descriptor : 快速排序
 */
public class QuickSort {
    private final static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        int[] array = createArray(1000000);
        int[] array2 = Arrays.copyOf(array, array.length);

        System.out.println(array.length == array2.length);

        final long start1 = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        System.out.println("times: " + count.get() + "\tuse: " + (System.currentTimeMillis() - start1));

        count.set(0);

        final long start2 = System.currentTimeMillis();
        quickSort2(array2, 0, array2.length - 1);
        System.out.println("times: " + count.get() + "\tuse: " + (System.currentTimeMillis() - start2));

        System.out.println(array == array2);
    }

    /**
     * 快速排序
     *
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[] array, int low, int high) {
        count.incrementAndGet();
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int tmp = array[low];

        while (i < j) {
            while (array[j] >= tmp && i < j) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
            }
            while (array[i] <= tmp && i < j) {
                i++;
            }
            if (i < j) {
                swap(array, i, j);
            }
        }
        quickSort(array, low, i - 1);
        quickSort(array, i + 1, high);
    }

    /**
     * 快速排序
     *
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort2(int[] array, int low, int high) {
        count.incrementAndGet();
        int pivot;
        if (low < high) {
            pivot = oneSort(array, low, high);
            quickSort2(array, low, pivot - 1);
            quickSort2(array, pivot + 1, high);
        }
    }

    /**
     * 一趟排序
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    public static int oneSort(int[] array, int low, int high) {
        int key = array[low];
        while (low < high) {
            while (high > low && array[high] >= key) {
                high--;
            }
            swap(array, low, high);
            while (low < high && array[low] <= key) {
                low++;
            }
            swap(array, low, high);
        }
        return low;
    }

    /**
     * 交换位置
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] createArray(int length) {
        int[] arr = new int[length];
        final Random random = new Random(length);
        for (int i = 0; i < length - 1; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }
}
