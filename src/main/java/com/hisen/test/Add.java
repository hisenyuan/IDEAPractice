package com.hisen.test;


/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/26 12:12
 */
public class Add {
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int now = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (now + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 7, 11, 15};
//        final int[] ints = twoSum(nums, 9);
//        System.out.println(Arrays.toString(ints));
        String s = "hisennnniiiiiissssssss";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        int max = 0;
        int str = 0;
        final int[] strs = new int[126];
        for (int i = 1; i < s.length(); i++) {
            final int n = s.charAt(i);
            strs[n] = strs[n] + 1;
            if (strs[n] > max) {
                max = strs[n];
                str = n;
            }
        }
        final StringBuilder sb = new StringBuilder();
        final char c = (char) Integer.parseInt(String.valueOf(str));
        for (int i = 0; i < max; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
