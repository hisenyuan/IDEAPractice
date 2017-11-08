package com.hisen.Algorithms.sort;

public class Sort_Test {

  /*
   * 冒泡排序 时间复杂度 T(n²) 空间复杂度 O(1);
   */
  public static int[] BubbleSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        // > 递减 <递增
        if (a[i] > a[j]) {
          Swap(a, i, j);
        }
      }
    }
    return a;
  }

  /*
   * 改进了的冒泡算法 少量交换后若整体有序，则停止循环 时间复杂度 T(n²) 空间复杂度 O(1)
   */
  public static int[] BubbleSortBetter(int[] a) {
    Boolean flag = true;// 标记
    /* 若flag为true则退出循环 */
    for (int i = 0; i < a.length && flag; i++) {
      flag = false;
      for (int j = a.length - 2; j >= i; j--) {
        /* 交换 j j+1 的值 */
        if (a[j] > a[j + 1]) {
          Swap(a, j, j + 1);
          /* 如果有数据交换则为真 */
          flag = true;
        }
      }
    }
    return a;
  }

  /**
   * 简单选择排序
   */
  public static int[] SimpleSelectionSort(int[] a) {
    int min;
    for (int i = 0; i < a.length; i++) {
      /* 将当前下标定义为最小值下标 */
      min = i;
      /* 循环之后的数据 */
      for (int j = i + 1; j < a.length; j++) {
        /* 如果有小于当前最小值的关键字，将此关键字下标给min */
        if (a[min] > a[j]) {
          min = j;
        }
      }
      /* 若min 不等于 i 说明找到最小值，交换 */
      if (i != min) {
        Swap(a, i, min);
      }
    }
    return a;
  }

  /**
   * 直接插入排序
   */
  public static int[] StraightInerstionSort(int[] a) {
    int i, j;
    for (i = 2; i <= a.length - 1; i++) {
      /* 将a[i] 插入有子序表 */
      if (a[i] < a[i - 1]) {
        /* 设置哨兵 */
        a[0] = a[i];
        for (j = i - 1; a[j] > a[0]; j--)
          /* 记录后移 */ {
          a[j + 1] = a[j];
        }
				/* 插入到正确位置 */
        a[j + 1] = a[0];
      }
    }
    return a;
  }

  /**
   * 换不同的间距
   **/
  public static int[] ShellSort(int[] a) {
    int i, j;
    int increment = a.length;
    do {
      increment = increment / 3 + 1;
      for (i = increment + 1; i <= a.length - 1; i++) {
        if (a[i] < a[i - increment]) {
          a[0] = a[i];
          for (j = i - increment; j > 0 && a[0] < a[j]; j -= increment) {
            a[j + increment] = a[j];
          }
          a[j + increment] = a[0];
        }
      }
    } while (increment > 1);
    return a;
  }

  /**
   * 堆排序
   */
  public static int[] HeapSort(int[] a) {
    int i;
    /* 把a中的r构建成一个大顶堆 */
    for (i = a.length / 2; i > 0; i--) {
      HeapAdjust(a, i, a.length - 1);
    }
    for (i = a.length - 1; i >= 0; i--) {
      Swap(a, 1, i);
      HeapAdjust(a, 1, i - 1);
    }
    return a;
  }

  /**
   * 归并排序
   */
  public static int[] MergeSort(int[] a) {
    MSort(a, a, 0, a.length - 1);
    return a;
  }

  /* 将a[s...t]归并排序为b[s...t] */
  public static void MSort(int a[], int[] b, int s, int t) {
    int m;
    int[] c = new int[30];
    if (s == t) {
      b[s] = a[s];
    } else {
      m = (s + t) / 2; // 将a[s...t]分为a[s...m]和a[m+1,t]
      MSort(a, c, s, m);/* 将a[s...m]归并为有序b[s...m] */
      MSort(a, c, m + 1, t);/* 将a[m+1...t]归并为有序b[m+1...t] */
      Merge(c, b, s, m, t);/* 将c[s...m]和c[m+1...t]归并到b[s...t] */
    }
  }

  public static void Merge(int[] a, int[] b, int i, int m, int n) {
    int j, k, l;
    /* 将a中的记录由小到大归并到b */
    for (j = m + 1, k = i; i <= m && j <= n; k++) {
      if (a[i] < a[j]) {
        b[k] = a[i++];
      } else {
        b[k] = a[j++];
      }
    }
    if (i <= m) {
      for (l = 0; l <= m - i; l++) {
        b[k + l] = a[i + l];/* 将剩余的a[i...m]复制到b */
      }
    }
    if (j <= n) {
      for (l = 0; l <= n - j; l++) {
        b[k + l] = a[i + l];/* 将剩余的a[j...n]复制到b */
      }
    }
  }

  /**
   * 快速排序
   */
  public static int[] QuickSort(int[] a) {
    Qsort(a, 1, a.length - 1);
    return a;
  }

  static void Qsort(int[] a, int low, int high) {
    int pivot;
    if (low < high) {
      pivot = Partition(a, low, high);
      Qsort(a, low, pivot - 1);
      Qsort(a, pivot + 1, high);
    }
  }

  public static int Partition(int[] a, int low, int high) {
    int pivotkey;
    pivotkey = a[low];
    while (low < high) {
      while (low < high && a[high] >= pivotkey) {
        high--;
      }
      Swap(a, low, high);
      while (low < high && a[high] <= pivotkey) {
        low++;
      }
      Swap(a, low, high);
    }
    return low;
  }

  /*
   * 数组数据交换
   */
  public static void Swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /* 调整a[s] 的关键字，使a[s....m]成为一个大顶堆 */
  public static void HeapAdjust(int[] a, int s, int m) {
    int temp, j;
    temp = a[s];
    /* 沿关键字较大的孩子节点向下筛选 */
    for (j = 2 * s; j < m; j *= 2) {
      if (j < m && a[j] < a[j + 1])
        /* j为关键字中较大记录的下标 */ {
        ++j;
      }
      if (temp >= a[j])
				/* 应该插入在s位置 */ {
        break;
      }
      a[s] = a[j];
      s = j;
    }
		/* 插入 */
    a[s] = temp;
  }

  public static void main(String[] args) {
    int[] a = {1, 2, 3, 7, 4, 6, 5, 9, 22, 12, 15};
    // int[] b = BubbleSort(a); //简单冒泡排序

    // int[] b = BubbleSortBetter(a); //改进冒泡排序 n²

    // int[] b = SimpleSelectionSort(a);// 简单选择排序，n²，优于冒泡

    // int[] b = StraightInerstionSort(a);
    /*
		 * 直接插入排序 n²/4，优于简单选择 第一个数据会失效，输出：15,2,3,4,5,6,7,9,12,15,22
		 */

    // int[] b = ShellSort(a);
    /*
		 * 希尔排序，n^3/2 ，优于直接排序，超越了n² 第一个数据会失效 输出：12,2,3,4,5,6,7,9,12,15,22
		 */

    // int[] b=HeapSort(a);
    /* 堆排序，nlogn，输出：3,1,2,4,5,6,7,9,12,15,22 */

    // int[] b = MergeSort(a);//归并排序，nlogn,空间：n+logn

    int[] b = QuickSort(a);// 快速排序，nlogn,空间：logn~n
    String s = "";
    for (int i = 0; i < b.length; i++) {
      s += b[i] + ",";
    }
    System.out.println(s);
  }
}
