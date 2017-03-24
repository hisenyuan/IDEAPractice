package com.hisen.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by hisenyuan on 2017/3/23 at 18:02.
 */
public class test {
    public static void main(String[] args) {
        String s = "{'A':'a'}";
        JSONObject obj= JSON.parseObject(s);
        System.out.println(obj.get("A"));
    }
}
