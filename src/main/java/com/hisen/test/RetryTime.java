package com.hisen.test;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/10/11 12:41
 */
public class RetryTime {
    @Test
    public void retryWaitFibonacciStrategy() {
        for (int i = 1; i < 15; i++) {
            System.out.println(count4time(i) * 5);
        }
    }

    public static int count4time(int n) {
        if (n==1 || n ==2){
            return 1;
        }
        return count4time(n-1) + count4time(n-2);
    }

    public static void main(String[] args) {
        System.out.println(0>0);
    }
}
