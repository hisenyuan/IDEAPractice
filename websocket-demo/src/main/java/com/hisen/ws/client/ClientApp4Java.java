package com.hisen.ws.client;

import com.hisen.ws.util.Constants;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ClientApp4Java {
    /**
     * java 客户端，可以给websocket发送消息
     *
     * @throws IOException
     * @throws WebSocketException
     * @throws InterruptedException
     */
    @Test
    public void testClientApp4Java() throws IOException, WebSocketException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.METHOD, Constants.METHOD_SINGLE);
        params.put(Constants.USER, "ClientApp4Java");
        params.put(Constants.SEND_TO, "hisen");
        String url = "ws://localhost:8080/websocket?";
        String uri = url + getStrFromMap(params);
        System.out.println(uri);
        WebSocket websocket = new WebSocketFactory()
                // 发送地址和参数
                .createSocket(uri)
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
            websocket.sendText("hisenyuan" + i);
            Thread.sleep(1000);
        }
        // 不关闭服务端会报错
        websocket.disconnect();
    }

    /**
     * 把map参数转换为 get请求参数
     * @param params
     * @return
     */
    private String getStrFromMap(HashMap<String, String> params) {
        return params.entrySet()
                .stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}
