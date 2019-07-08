package com.hisen.string.stringbuilder;

import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/4/17 22:11
 * @description 删除Str最后一个字符
 */
public class TestSBDelete {
    @Test
    public void testDeleteCharAt(){
        StringBuilder builder = new StringBuilder();
        builder.append("a").append("=").append("b").append("&");
        System.out.println(builder.deleteCharAt(builder.length()-1).toString());
    }
}
