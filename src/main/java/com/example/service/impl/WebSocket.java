package com.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by qidd on 2018-4-1
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();


    @OnOpen
    public void OnOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);


    }

    @OnClose
    public void OnClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void OnMessage(String message) {
        log.info("收到消息={}", message);
    }

    public void sendmessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            try {

                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.info("消息={}", e.getMessage());
                e.printStackTrace();
            }
        }
    }


}
