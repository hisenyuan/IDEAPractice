package com.hisen.collection.list.duplicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author hisenyuan
 * @time 2018/2/7 12:40
 * @description 对象去重
 */
public class DuplicateMethod {

  public static void main(String[] args) {
    ArrayList<PersonBean> beans = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      beans.add(new PersonBean(i,"hisen"+i,i*10));
      beans.add(new PersonBean(i,"hisen"+i*2,i*10*2));
    }
    System.out.println(beans.size());
    //
    List<PersonBean> unique = beans.stream().collect(
        Collectors.collectingAndThen(
            Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparingLong(PersonBean::getAge))),
            ArrayList::new
        )
    );
    System.out.println(unique.size());

    //
    List<PersonBean> personBeans = beans.stream().filter(distinctByKey(p -> p.getAge())).collect(
        Collectors.toList());
    System.out.println(personBeans.size());

    //
    TreeSet<PersonBean> personSet = new TreeSet<>(Comparator.comparing(PersonBean::getAge));
    personSet.addAll(beans);
    ArrayList<PersonBean> list = new ArrayList<>(personSet);
    System.out.println(list.size());
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
    ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE) == null;
  }
}
