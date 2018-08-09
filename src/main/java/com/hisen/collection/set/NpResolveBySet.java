package com.hisen.collection.set;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NpResolveBySet {
    public static void main(String[] args) {
        //创建一个set集合存储要访问的目标，不可重复
        Set<String> state_need = new HashSet<>();
        state_need.add("mt");
        state_need.add("wa");
        state_need.add("or");
        state_need.add("id");
        state_need.add("nv");
        state_need.add("ut");
        state_need.add("ca");
        state_need.add("az");

        //用map散列表的形式表示每个广播平台最大覆盖城市量，key:广播平台的名称  value（set集合）:覆盖的城市
        Map<String, HashSet<String>> station = new HashMap<>();
        HashSet<String> setOne = new HashSet<>();
        setOne.add("id");
        setOne.add("nv");
        setOne.add("ut");
        station.put("kone", setOne);

        HashSet<String> setTwo = new HashSet<>();
        setTwo.add("wa");
        setTwo.add("id");
        setTwo.add("mt");
        station.put("ktwo", setTwo);

        HashSet<String> setThree = new HashSet<>();
        setThree.add("or");
        setThree.add("nv");
        setThree.add("ca");
        station.put("kthree", setThree);

        HashSet<String> setFour = new HashSet<>();
        setFour.add("nv");
        setFour.add("ut");
        station.put("kfour", setFour);

        HashSet<String> setFive = new HashSet<>();
        setFive.add("ca");
        setFive.add("az");
        station.put("kfive", setFive);
        //创建一个集合存储最后选择的广播平台
        HashSet<String> final_station = new HashSet<>();
        while (!state_need.isEmpty()) {
            String best_station="";
            HashSet<String> states_covered = new HashSet<>();
            for (Map.Entry e : station.entrySet()) {
                HashSet<String> covered = new HashSet<>();
                covered.addAll(state_need);
                HashSet<String> sta = (HashSet<String>) e.getValue();
                covered.retainAll(sta);
                if (covered.size() > states_covered.size()) {
                    best_station = (String) e.getKey();
                    states_covered.addAll(sta);
                    final_station.add(best_station);
                    state_need.removeAll(states_covered);
                }
            }

        }
        System.out.println(JSON.toJSONString(final_station));

    }
}
