package com.hisen.thread;

import com.hisen.utils.CommonUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author hisenyuan
 * @Description CompletableFuture 简单应用
 * @Date 2019/4/26 11:17
 */
public class CompletableFutureTest {
    @Test
    public void test() {
        // 生成一个线程池
        final ExecutorService threadPoolExecutor = CommonUtils.getThreadPoolExecutor("hisen", 50, 100, 1024);


        List<Integer> nl = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nl.add(i);
        }

        handleByCompletableFuture(threadPoolExecutor, nl);

        handleBySerialize(nl);
    }

    /**
     * 串行执行 nl.seze() = 100 , use time(ms):45772
     *
     * @param nl 任务列表
     */
    private void handleBySerialize(List<Integer> nl) {
        final long start = System.currentTimeMillis();
        nl.forEach(e -> System.out.println(getHisenByAge(e)));
        System.out.println("use time(ms):" + (System.currentTimeMillis() - start));
    }

    /**
     * 线程池 + CompletableFuture 执行(超时300毫秒放弃) nl.seze() = 100 ， use time(ms):1478
     *
     * @param threadPoolExecutor 线程池
     * @param nl                 任务列表
     */
    private void handleByCompletableFuture(ExecutorService threadPoolExecutor, List<Integer> nl) {
        final long start = System.currentTimeMillis();
        final List<CompletableFuture<String>> collect = nl.stream()
                .map(e -> CompletableFuture
                        // 提交任务到线程池
                        .supplyAsync(() -> getHisenByAge(e), threadPoolExecutor)
                        // 处理完之后执行toString方法(类似于回调方法)
                        .thenApplyAsync(Hisen::toString)
                )
                .collect(Collectors.toList());

        // 添加完发出停止信号，之后会执行拒绝策略
        threadPoolExecutor.shutdown();

        // 遍历结果，超时300毫秒的放弃
        collect.forEach(e -> {
            try {
                System.out.println(e.get(300, TimeUnit.MILLISECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e1) {
                System.out.println("timeout");
            }
        });
        System.out.println(collect.size());
        System.out.println("use time(ms):" + (System.currentTimeMillis() - start));
    }


    private Hisen getHisenByAge(Integer age) {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Hisen hisen = new Hisen();
        hisen.setName("hisen" + age);
        hisen.setAge(age);
        return hisen;
    }

    class Hisen {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Hisen.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("age=" + age)
                    .toString();
        }
    }
}
