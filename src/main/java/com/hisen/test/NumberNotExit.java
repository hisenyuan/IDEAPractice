package com.hisen.test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/1/5 18:19
 */
public class NumberNotExit {
    private final static Integer NUM = 100000000;

    public static void main(String[] args) {
        final Random random = new Random();
        List<Integer> list = new ArrayList<>();
        final long start = System.nanoTime();
        // create a random list
        for (int i = 0; i < NUM; i++) {
            final int randomRes = random.nextInt(NUM);
            list.add(randomRes);
        }
        System.out.println("list size:" + list.size());

        // create a bitset
        final BitSet bitSet = new BitSet(NUM);
        for (Integer aList : list) {
            bitSet.set(aList);
        }

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < bitSet.length(); i++) {
            // also can print the number
            if (!bitSet.get(i)) {
                atomicInteger.incrementAndGet();
            }
        }
        System.out.println("natural number 0 ~ " + NUM + " not exit in random list size is:" + atomicInteger.intValue());
        final long randomListCreateTime = System.nanoTime() - start;
        System.out.println("use time(ns):" + randomListCreateTime);
    }
}
