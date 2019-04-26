package com.hisen.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/26 11:18
 */
public class CommonUtils {
    // 初始化一个连接池
    public static ExecutorService getThreadPoolExecutor(String threadName, int core, int max, int queueSize) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(threadName).build();
        return new ThreadPoolExecutor(
                core,
                max,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
