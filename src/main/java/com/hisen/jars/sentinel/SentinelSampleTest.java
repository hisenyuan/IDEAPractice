package com.hisen.jars.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/27 10:15
 */
public class SentinelSampleTest {
    public static void main(String[] args) {
        // 流控标识
        String key = "HelloWorld";
        initFlowRules(key);
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry(key);
                System.out.println("HelloWorld\t" + new Date());
            } catch (BlockException e) {
//                不打印，不然全部都打印出阻塞的信息
//                System.out.println("block!");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }
//    输出的结果，一秒钟两个输出
//    HelloWorld	Sat Apr 27 10:31:10 CST 2019
//    HelloWorld	Sat Apr 27 10:31:10 CST 2019
//    HelloWorld	Sat Apr 27 10:31:11 CST 2019
//    HelloWorld	Sat Apr 27 10:31:11 CST 2019
//    HelloWorld	Sat Apr 27 10:31:12 CST 2019
//    HelloWorld	Sat Apr 27 10:31:12 CST 2019
//    HelloWorld	Sat Apr 27 10:31:13 CST 2019
//    HelloWorld	Sat Apr 27 10:31:13 CST 2019
//    HelloWorld	Sat Apr 27 10:31:14 CST 2019
//    HelloWorld	Sat Apr 27 10:31:14 CST 2019
//    HelloWorld	Sat Apr 27 10:31:15 CST 2019
//    HelloWorld	Sat Apr 27 10:31:15 CST 2019
//    HelloWorld	Sat Apr 27 10:31:16 CST 2019
//    HelloWorld	Sat Apr 27 10:31:16 CST 2019
//    HelloWorld	Sat Apr 27 10:31:17 CST 2019
//    HelloWorld	Sat Apr 27 10:31:17 CST 2019

    // 流控规则
    private static void initFlowRules(String key) {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        // 注意是resourse 不是RefResource
        flowRule.setResource(key);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(2);
        ruleList.add(flowRule);
        FlowRuleManager.loadRules(ruleList);
    }
}
