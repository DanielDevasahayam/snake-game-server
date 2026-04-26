package com.game.network;

import com.game.dto.MatchResultDTO;
import com.game.dto.UserDataDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class InGameSessionHandler extends StompSessionHandlerAdapter {

    private UserDataDTO userDTO;

    private CompletableFuture<String> future;
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected");

    }
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return MatchResultDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        MatchResultDTO matchResultDTO = (MatchResultDTO) payload;

        if (matchResultDTO.getType().equals("START_GAME")) {
        }

    }
}