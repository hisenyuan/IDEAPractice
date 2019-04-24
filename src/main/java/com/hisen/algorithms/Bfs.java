package com.hisen.algorithms;

import com.google.common.collect.Maps;

import java.util.*;

public class Bfs {
    public static void main(String[] args) {
        search("you");
    }

    /**
     * 广度优先搜索，map的key为每一个节点，key中的值为当前节点相邻的节点
     * @param name
     */
    private static void search(String name) {
        HashMap<String, List<String>> map = Maps.newHashMap();
        List<String> youv = Arrays.asList("alice", "bob", "claire");
        map.put("you", youv);
        List<String> bobv = Arrays.asList("anuj", "peggy");
        map.put("bob", bobv);
        List<String> alicev = Arrays.asList("peggy");
        map.put("alice", alicev);
        List<String> clairev = Arrays.asList("thom", "jonny");
        map.put("claire", clairev);
        List<String> anujv = new ArrayList<>();
        map.put("anuj", anujv);
        List<String> peggyv = new ArrayList<>();
        map.put("peggy", peggyv);
        List<String> thomv = new ArrayList<>();
        map.put("thom", thomv);
        List<String> jonnyv = new ArrayList<>();
        map.put("jonny", jonnyv);

        LinkedList<String> queue = new LinkedList<>();
        map.get(name).forEach(queue::addFirst);
        // 已经检查过
        List<String> searched = new ArrayList<>();
        while (!queue.isEmpty()) {
            String person = queue.removeLast();
            System.out.println("node:" + person);
            // 如果当前节点没有检查过
            if (!searched.contains(person)) {
                // 满足条件
                if (person.equals("jonny")) {
                    System.out.println("find:" + person);
                    return;
                } else {
                    // 把当前节点的相邻节点加入
                    map.get(person).forEach(queue::addFirst);
                    searched.add(person);
                }
            }
        }

    }
}
