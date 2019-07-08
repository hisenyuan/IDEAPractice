package com.hisen.algorithms;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author hisenyuan
 * @Description Rotate Array https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
 * @Date 2019/4/26 16:02
 */
public class RotateArray {
    @Test
    public void testRotate() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 9;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    private void rotate(int[] nums, int k) {
        final int length = nums.length;
        if (k > length) {
            k = k % length;
        }

        int[] tmp = new int[k];

        System.arraycopy(nums, length - k, tmp, 0, k);

        for (int i = length - 1; i >= 0; i--) {
            if (i >= k) {
                nums[i] = nums[i - k];
            } else {
                nums[i] = tmp[i];
            }
        }
    }
}
