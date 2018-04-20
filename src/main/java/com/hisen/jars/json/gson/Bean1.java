package com.hisen.jars.json.gson;

import java.util.Date;

/**
 * @author hisenyuan
 * @time 2018/4/16 15:53
 * @description
 */
public class Bean1 {
    private Date now;
    private String name;
    private Long height;

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
