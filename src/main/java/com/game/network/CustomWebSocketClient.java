package com.game.network;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

public class CustomWebSocketClient {

    private StompSession stompSession;

    public void connectToWebSocket() throws Exception {

        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSession session = null;
        String url = "http://localhost:8080/ws";
        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        try {
            session = stompClient.connectAsync(url, sessionHandler).get();
            session.subscribe("/topic/queue", sessionHandler);
            System.out.println("Sending message");
            session.send("/app/findPlayersInQueue", "MESSI");
//            Thread.sleep(300000);
        } finally {
        }
    }


}

