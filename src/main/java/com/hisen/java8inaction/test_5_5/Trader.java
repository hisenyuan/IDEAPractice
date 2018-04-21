package com.hisen.java8inaction.test_5_5;

/**
 * @author hisenyuan
 * @time 2018/4/21 16:33
 * @description
 */
public class Trader {
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    @Override
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
