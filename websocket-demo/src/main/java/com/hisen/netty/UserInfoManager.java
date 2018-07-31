package com.hisen.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hisenyuan
 * @time 2018/7/31 10:08
 * @description
 */
public class UserInfoManager {
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private static ConcurrentMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<>();

    /**
     * 登录注册 channel
     */
    public static void addChannel(Channel channel, String uid) {
        String remoteAddr = channel.remoteAddress().toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        userInfos.put(channel, userInfo);
    }

    /**
     * 普通消息
     *
     * @param message
     */
    public static void broadcastMess(String uid, String message, String sender) {
        if (null != message && !"".equals(message)) {
            try {
                rwLock.readLock().lock();
                Set<Map.Entry<Channel, UserInfo>> entrySet = userInfos.entrySet();
                for (Map.Entry<Channel, UserInfo> e : entrySet) {
                    Channel ch = e.getKey();
                    UserInfo info = e.getValue();
                    if (uid.equals(info.getUserId())) {
                        // 发送消息
                        ch.writeAndFlush(new TextWebSocketFrame("发送者:" + sender + ",消息:" + message));
                    } else {
                        // 消息无人消费，存储在redis中，下次上线看是否有相应的匹配
                    }
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }

    public static void removeChannel(Channel channel) {
        userInfos.remove(channel);
    }

    public static UserInfo getUserInfo(Channel channel) {
        return userInfos.get(channel);
    }
}
