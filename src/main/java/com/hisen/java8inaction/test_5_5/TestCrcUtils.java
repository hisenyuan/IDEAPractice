package com.hisen.java8inaction.test_5_5;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/4/21 16:35
 * @description
 */
public class TestCrcUtils {
    @Test
    public void test55(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactions1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）:"+JSON.toJSONString(transactions1));

        List<String> transactions2 = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("(2) 交易员都在哪些不同的城市工作过？:"+JSON.toJSONString(transactions2));
//
        List<Trader> transactions3 = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("(3) 查找所有来自于剑桥的交易员，并按姓名排序:"+JSON.toJSONString(transactions3));
//            (4) 返回所有交易员的姓名字符串，按字母顺序排序。
//            (5) 有没有交易员是在米兰工作的？
//            (6) 打印生活在剑桥的交易员的所有交易额。
//            (7) 所有交易中，最高的交易额是多少？
//            (8) 找到交易额最小的交易。


    }
}
