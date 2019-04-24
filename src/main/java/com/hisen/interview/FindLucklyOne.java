package com.hisen.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author hisenyuan
 * @time 2018/7/20 17:04
 * @description 随机查找一个幸运儿 - 值班的人 23333
 */
public class FindLucklyOne {
    public static void main(String[] args) {
        List<String> person = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
        List<String> lucklys = new ArrayList<>();
        for (int i = 0; i < person.size() * 50; i++) {
            if (i % 8 == 0){
                System.out.println("=================");
            }
            findLucklyOneByHashCode(person, lucklys);
        }
    }

    /**
     *
     * @param person 目前所有的人
     * @param lucklys 这一轮已经值班了的人
     */

    private static void findLucklyOneByHashCode(List<String> person, List<String> lucklys) {
        List<String> result = new ArrayList<>(person);
        result.removeAll(lucklys);
        if (result.size() == 0) {
            lucklys.clear();
            result = new ArrayList<>(person);
        }
        String code = System.nanoTime() + UUID.randomUUID().toString();
        int index = Math.abs(code.hashCode()) % result.size();
        String lucklyOne = result.get(index);
        System.out.println("lucklyOne:" + lucklyOne);
        lucklys.add(lucklyOne);
    }
    /**
     * hashCode随机取出来的数据
     * lucklyOne:G
     * lucklyOne:B
     * lucklyOne:C
     * lucklyOne:D
     * lucklyOne:H
     * lucklyOne:F
     * lucklyOne:E
     * lucklyOne:A
     */
}
