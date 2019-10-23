package com.hisen.algorithms.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author hisenyuan
 * @date 2019-10-23 17:40
 * url:https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/
 */
public class MoveZeroes {
    @Test
    public void testMoveZeros() {
        int[] oriArray = {0, 0, 0, 0, 12, 0};
        moveZeroes(oriArray);
        System.out.println(Arrays.toString(oriArray));
    }

    private void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int value : nums) {
            if (value == 0) {
                zeroCount = zeroCount + 1;
            }
        }
        while (zeroCount-- >= 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0 && i + 1 < nums.length) {
                    int tmp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }
}
