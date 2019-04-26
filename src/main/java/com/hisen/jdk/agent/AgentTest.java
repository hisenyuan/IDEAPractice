package com.hisen.jdk.agent;

import com.hisen.jdk.agent.service.Fruit;
import com.hisen.jdk.agent.service.impl.Apple;
import com.hisen.jdk.agent.service.impl.Orange;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/29 23:40
 */
public class AgentTest {
    public static void main(String[] args) {
        System.out.println("===CGlibAgentTest===");
        cGlibAgentTest();

        System.out.println("\n===JDKDynamicAgentTest===");
        jdkDynamicAgentTest();
    }

    private static void cGlibAgentTest() {
        CGlibAgent cGlibAgent = new CGlibAgent();
        Apple apple = (Apple) cGlibAgent.getInstance(Apple.class);
        apple.show();

        System.out.println();

        Orange orange = (Orange) cGlibAgent.getInstance(Orange.class);
        orange.show();
    }

    private static void jdkDynamicAgentTest() {
        // must be return interface
        Fruit apple = (Fruit) DynamicAgent.agent(Fruit.class, new Apple());
        apple.show();

        System.out.println();

        Fruit orange = (Fruit) DynamicAgent.agent(Fruit.class, new Orange());
        orange.show();
    }

//===CGlibAgentTest===
//-> before invoking
//Apple show method is invoked
//-> after invoking
//
//-> before invoking
//    Orange show method is invoked
//-> after invoking
//
//===JDKDynamicAgentTest===
//            -> before invoking
//    Apple show method is invoked
//-> after invoking
//
//-> before invoking
//    Orange show method is invoked
//-> after invoking
}
