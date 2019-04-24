package com.hisen.netty;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ClientApp4Java {
    /**
     * java 客户端，可以给websocket发送消息
     *
     * @throws IOException
     * @throws WebSocketException
     * @throws InterruptedException
     */
    @Test
    public void testClientApp4Java() throws IOException, WebSocketException {
        WebSocket websocket = new WebSocketFactory()
                // 发送地址和参数
                .createSocket("ws://10.1.1.157:9999/ws")
                // 新增一个监听者
                .addListener(new WebSocketAdapter() {
                    @Override
                    public void onTextMessage(WebSocket ws, String message) {
                        // 接收到的信息
                        System.out.println(message);
                    }
                })
                .connect();
        for (int i = 0; i < 10; i++) {
            // 消息格式 sender,uid,message
            websocket.sendText("hisen,1,test" + i);
        }
        // 不关闭服务端会报错
        websocket.disconnect();
    }
}
