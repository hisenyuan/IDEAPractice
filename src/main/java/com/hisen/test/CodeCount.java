package com.hisen.test;

import com.alibaba.fastjson.JSON;
import com.sun.tools.javac.code.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/5 15:30
 */
public class CodeCount {
    public static void main(String[] args) {
        String str = "Edianzu|1224|854|370\n" +
                "Sierou-Java|3275|1969|1306\n" +
                "jiachenghao|2772|523|2249\n" +
                "peiyao.zhang|3623|2320|1303\n" +
                "chenping|1935|156|1779\n" +
                "hanzhong|775|993|-218\n" +
                "hisenyuan|184|129|55\n" +
                "zzl|154|10|144\n" +
                "Edianzu|295|65|230\n" +
                "hanzhong|39|1|38\n" +
                "hanzhong|1616|1075|541\n" +
                "hisenyuan|90|124|-34\n" +
                "chenping|4|3|1\n" +
                "hanzhong|681|111|570\n" +
                "hisenyuan|435|84|351\n" +
                "chenping|148|41|107\n" +
                "guoshenting|25|10|15\n" +
                "hanzhong|4697|3374|1323\n" +
                "hisenyuan|5668|4532|1136\n" +
                "liqiang|32|26|6\n" +
                "Sierou-Java|16|4|12\n" +
                "hanzhong|16|1|15\n" +
                "hisenyuan|13|0|13\n" +
                "jiachenghao|43|1|42\n" +
                "peiyao.zhang|21|6|15\n" +
                "zzl|8|2|6\n" +
                "hisenyuan|2|2|0\n" +
                "Edianzu|14|10|4\n" +
                "hisenyuan|115|94|21\n" +
                "jiachenghao|20|2|18\n" +
                "Edianzu|2|2|0\n" +
                "Sierou-Java|163|77|86\n" +
                "Sierou-Java|67|4|63\n" +
                "hanzhong|1911|1538|373\n" +
                "hisenyuan|2752|1943|809\n" +
                "jiachenghao|89|12|77\n" +
                "zhangyao|13|10|3\n" +
                "hanzhong|14|0|14\n" +
                "hisenyuan|585|34|551";

        String[] all = str.split("\n");
        final ArrayList<Count> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            final String[] one = all[i].split("\\|");
            final Count count = new Count();
            count.setName(one[0]);
            count.setAdded(Integer.parseInt(one[1]));
            count.setRemoved(Integer.parseInt(one[2]));
            count.setTotal(Integer.parseInt(one[3]));
            list.add(count);
        }
        final Map<String, List<Count>> listMap = list.stream().collect(Collectors.groupingBy(Count::getName));
        System.out.printf("%-15s%-15s%-15s%s\n", "name", "added", "removede", "total");
        for (Map.Entry<String, List<Count>> per : listMap.entrySet()) {

            final String name = per.getValue().get(0).getName();
            final int added = per.getValue().stream().map(Count::getAdded).mapToInt(Integer::valueOf).sum();
            final int tremovede = per.getValue().stream().map(Count::getRemoved).mapToInt(Integer::valueOf).sum();
            final int total = per.getValue().stream().map(Count::getTotal).mapToInt(Integer::valueOf).sum();

            System.out.printf("%-15s%-15s%-15s%s\n", name, added, tremovede, total);
        }
    }

    static class Count {
        private String name;
        private int added;
        private int removed;
        private int total;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
