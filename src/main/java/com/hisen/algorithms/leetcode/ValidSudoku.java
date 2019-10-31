package com.hisen.algorithms.leetcode;

/**
 * @author hisenyuan
 * @date 2019-10-31 19:52
 * url:https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] borad = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(borad));

    }
    public static boolean isValidSudoku(char[][] board) {
        // 处理 9 * 9
        for (int i = 0; i < board.length; i++) {
            int[] rowFlag = new int[10];
            int[] columnFlag = new int[10];
            for (int j = 0; j < board.length; j++) {
                char row = board[i][j];
                char column = board[j][i];
                if (check(rowFlag, row)) {
                    return false;
                }
                if (check(columnFlag, column)) {
                    return false;
                }
                // 处理 3 * 3
                if (i % 3 == 0 && j % 3 == 0) {
                    int[] smallFlag = new int[10];
                    for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            char now = board[k][l];
                            if (check(smallFlag, now)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean check(int[] flag, char now) {
        boolean res = true;
        if (now != '.') {
            int index = now - '0';
            flag[index] += 1;
            if (flag[index] > 1) {
                res = false;
            }
        }
        return !res;
    }
}
