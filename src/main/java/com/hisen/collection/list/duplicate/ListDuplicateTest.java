package com.hisen.collection.list.duplicate;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author hisenyuan
 * @time 2018/4/19 14:06
 * @description list对象根据属性去重 lambda + stream
 */
public class ListDuplicateTest {


    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setAge(10);
            person.setName("hsien");
            person.setWeight(i);
            list.add(person);
        }
        Person bean = new Person();
        bean.setName("hisenyuan");
        bean.setAge(33);
        bean.setWeight(65);
        list.add(bean);

        List<Person> collect = list.stream().filter(distinctByKey(Person::getName))
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * 过滤方法
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>(16);
        return t -> map.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE) == null;

//        这个也可以，不过感觉效率要低一些，线程不是那么安全
//        Set<Object> seen = ConcurrentHashMap.newKeySet();
//        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * 内部类
     */
    static class Person {
        private int age;
        private String name;
        private int weight;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public Person() {
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
