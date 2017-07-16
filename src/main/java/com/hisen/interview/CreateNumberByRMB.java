package com.hisen.interview;

import org.junit.Test;

/**
 * 动态规划问题
 * 根据人民币的币值 1、5、20、50、100
 * 判断一个特定的金额有多少中组合
 * Created by hisenyuan on 2017/7/15 at 22:34.
 */
public class CreateNumberByRMB {
  @Test
  public void test3() {
    int[] rmb = {1, 5, 10, 20, 50, 100};
    int i = countWays(rmb, 5, 1000000);
    System.out.println(i);
  }
  /**
   * 第三组 - 动态规划
   * @param penny
   * @param n
   * @param aim
   * @return
   */
  public int countWays(int[] penny, int n, int aim) {
    // write code here
    if(n==0||penny==null||aim<0){
      return 0;
    }
    int[][] pd = new int[n][aim+1];
    for(int i=0;i<n;i++){
      pd[i][0] = 1;
    }
    for(int i=1;penny[0]*i<=aim;i++){
      pd[0][penny[0]*i] = 1;
    }
    for(int i=1;i<n;i++){
      for(int j=0;j<=aim;j++){
        if(j>=penny[i]){
          pd[i][j] = pd[i-1][j]+pd[i][j-penny[i]];
        }else{
          pd[i][j] = pd[i-1][j];
        }
      }
    }
    return pd[n-1][aim];
  }
}


