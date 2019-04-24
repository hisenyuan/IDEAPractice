package com.hisen.interview;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/4 18:50
 */
public class FindNotDupInArray {
    public static void main(String[] args) {
        int[] mayDup = {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int res = mayDup[0];
        for (int i = 1; i < mayDup.length; i++) {
            System.out.print("res^mayDup[i]: " + res + "^" + mayDup[i]);
            res ^= mayDup[i];
            System.out.println("=" + res);
        }
        System.out.println(res);
    }
}
