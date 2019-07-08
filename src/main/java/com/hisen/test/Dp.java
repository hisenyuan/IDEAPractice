package com.hisen.test;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/10 16:46
 */
public class Dp {

    public static void main(String[] args) {
        int[] ori = new int [200];
        for (int i = 0; i < 199; i++) {
            ori[i] = i + 1;
        }
        final int i = coinChange(ori, 2000);
        System.out.println(i);
    }
    public static int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;

        int[] dp = new int [amount+1];
        dp[0]=0; // do not need any coin to get 0 amount
        for(int i=1;i<=amount; i++)
            dp[i]= Integer.MAX_VALUE;
        for(int i=0; i<=amount; i++){
            for(int coin: coins){
                if(i+coin <=amount){
                    if(dp[i]==Integer.MAX_VALUE){
                        dp[i+coin] = dp[i+coin];
                    }else{
                        dp[i+coin] = Math.min(dp[i+coin], dp[i]+1);
                    }
                }
            }
        }

        if(dp[amount] >= Integer.MAX_VALUE)
            return -1;

        return dp[amount];
    }
}
