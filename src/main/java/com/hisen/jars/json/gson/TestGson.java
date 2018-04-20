package com.hisen.jars.json.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/4/16 15:54
 * @description 利用Gson转带有范型的对象
 */
public class TestGson {
    @Test
    public void parseA2B(){
        List<Bean1> bean1s = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Bean1 bean1 = new Bean1();
            bean1.setName("hisen_"+i);
            bean1.setNow(new Date());
            bean1.setHeight(100L+i);
            bean1s.add(bean1);
        }
        Gson gson = new Gson();
        String toJson = gson.toJson(bean1s);
        System.out.println(toJson);
        List<Bean2> bean2s=gson.fromJson(toJson, new TypeToken<List<Bean2>>(){}.getType());
        for (int i = 0; i < bean2s.size(); i++) {
            Date now = bean2s.get(i).getNow();
            System.out.println(now);
        }
        System.out.println(JSON.toJSONString(bean2s));
    }
}
