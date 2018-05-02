package com.hisen.java8inaction;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/5/2 15:14
 * @description
 */
public class Map2StringTest {

    @Test
    public void test() {
        HashMap<String, String> unSignMapData = new HashMap<>(8);
        unSignMapData.put("name","hisen");
        unSignMapData.put("age","25");
        unSignMapData.put("height","233");
        unSignMapData.put("addr","beijing");
        String signData = unSignMapData
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        System.out.println(signData);
    }
}
