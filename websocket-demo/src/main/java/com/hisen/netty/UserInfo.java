package com.hisen.netty;

import io.netty.channel.Channel;

/**
 * @author hisenyuan
 * @time 2018/7/31 10:09
 * @description
 */
public class UserInfo {
    /**
     * userId
     */
    private String userId;
    /**
     * 地址
     */
    private String addr;
    /**
     * 通道
     */
    private Channel channel;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
