package com.hisen.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPoolExample {
    private static org.apache.log4j.Logger LOGGER = Logger.getRootLogger();

    @Test
    public void testThreadExample() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("hisenyuan").build();
        ExecutorService pool = new ThreadPoolExecutor(
                20,
                50,
                10000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10240),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 50; i++) {
            // submit your executor
            pool.execute(this::printInfo);
        }
        pool.shutdown();
        try {
            while (!pool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                LOGGER.debug("Waiting for terminate");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    /**
     * something you want to deal
     */
    void printInfo() {
        for (int i = 0; i < 100; i++) {
            Thread t = Thread.currentThread();
            LOGGER.info(t.getId() + "\t" + t.getName() + "\t" + t.getState() + "\t" + System.nanoTime());
        }
    }
}
