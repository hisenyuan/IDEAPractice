package com.hisen.ws.server;

import com.hisen.ws.util.Constants;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketServer {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    // 实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 当前用户
    private String user;

    /**
     * 客户端可以是web页面，也可以是web controller
     * <p>
     * 通过连接或者message可以控制发送给谁
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        // 获取url传过来的参数
        Map<String, List<String>> parameterMap = session.getRequestParameterMap();
        // 发送方式
        String method = null;
        // 发送给哪些人
        List<String> receivers = new ArrayList<>();
        // 发送者
        String sernder = null;
        if (parameterMap.containsKey(Constants.METHOD)) {
            method = parameterMap.get(Constants.METHOD).get(0);
        }
        if (parameterMap.containsKey(Constants.SEND_TO)) {
            receivers = parameterMap.get(Constants.SEND_TO);
        }
        if (parameterMap.containsKey(Constants.USER)) {
            sernder = parameterMap.get(Constants.USER).get(0);
        }

        System.out.println("sender:" + sernder + ",receivers:" + receivers.toString() + ",method:" + method);
        if (method == null || method.equals(Constants.METHOD_ALL)) {
            //发送所有
            send2All(message);
        } else {
            //单发
            send2Users(receivers, message);
        }

    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.user = session.getRequestParameterMap().get(Constants.USER).get(0);
        // 放入map
        webSocketMap.put(user, this);
        //在线数加1
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount() + ",session:" + session.getId() + ",user:" + this.user);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 移除
        webSocketMap.remove(this.user);
        //在线数减1
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount() + ",user:" + this.user);
    }


    private void send2Users(List<String> receivers, String message) {
        receivers.forEach(e -> {
            System.out.println("single receiver:" + e);
            Optional.ofNullable(webSocketMap.get(e))
                    .filter(webSocketServer -> webSocketServer.session.isOpen())
                    .ifPresent(webSocketServer -> sendOnce(message, e, webSocketServer));
        });
    }

    private void send2All(String message) {
        webSocketMap.forEach((key, value) -> {
            sendOnce(message, key, value);
        });
    }

    private void sendOnce(String message, String e, WebSocketServer webSocketServer) {
        try {
            webSocketServer.sendMessage(message);
        } catch (IOException exp) {
            System.out.println("发送出错，receiver:" + e);
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误，user:" + this.user);
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}