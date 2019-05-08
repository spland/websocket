package com.websocket.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * create by xxie
 * on 2019/4/28
 */
@Component
@ServerEndpoint(value = "/websocket")
public class SocketServer {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<SocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;


    @OnOpen //建立连接方法
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        try {
            sendMessage("连接成功");
        } catch (IOException e) {

        }
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
    }
    @OnMessage
    public void onMessage(String message,Session session){
        webSocketSet.stream().forEach(item -> {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public static void sendAll() {

    }
    @OnError
    public void onError(Session session,Throwable error){

    }
}
