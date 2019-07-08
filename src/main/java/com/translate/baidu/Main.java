package com.translate.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Main {

    private static final String APP_ID = "20180226000127578";
    private static final String SECURITY_KEY = "_noh4JkwGf1kEtUsseTc";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "مع سّلامة";
        final String result = api.getTransResult(query, "aoto", "zh");

        System.out.println(result);

        final JSONObject parseObject = JSONObject.parseObject(result);
        final JSONArray trans_result = parseObject.getJSONArray("trans_result");
        final JSONObject res = trans_result.getJSONObject(0);
        final String src = res.get("src").toString();
        final String dst = res.get("dst").toString();
        System.out.println("源头: " + src + ", 结果:" + dst);
    }

}
