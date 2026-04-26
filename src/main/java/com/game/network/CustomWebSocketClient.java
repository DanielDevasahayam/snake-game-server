package com.game.network;

import com.game.dto.MatchResultDTO;
import com.game.dto.UserDataDTO;
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
import java.util.concurrent.CompletableFuture;

public class CustomWebSocketClient {



    private StompSession stompSession;
    CompletableFuture<MatchResultDTO> responseFuture = new CompletableFuture<>();
    public MatchResultDTO connectToWebSocket(UserDataDTO userDTO) throws Exception {

        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSession session = null;
        String url = "http://localhost:8080/ws";
        StompSessionHandler sessionHandler = new MyStompSessionHandler(userDTO, responseFuture);
        try {
            session = stompClient.connectAsync(url, sessionHandler).get();
            session.subscribe("/topic/queue", sessionHandler);
            System.out.println("Sending message");
            //get player ids

            session.send("/app/findPlayersInQueue", userDTO.getId().toString());
            MatchResultDTO matchResultDTO = responseFuture.get();
            if (matchResultDTO != null) {
                System.out.println(" match found");
                session.subscribe("/topic/game" + "/" +
                        matchResultDTO.getRoomId(), new InGameSessionHandler(userDTO, new CompletableFuture<>()));
                session.send("/app/game/" + matchResultDTO.getRoomId(), MatchResultDTO.builder()
                        .roomId(matchResultDTO.getRoomId())
                        .type("READY")
                        .playerId(userDTO.getId().toString()).build());
                return matchResultDTO;
            }

        } finally {

        }

        return null;
    }



}

