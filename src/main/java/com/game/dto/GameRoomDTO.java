package com.game.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameRoomDTO {
    private String roomId;
    private String opponent;
    private boolean gameStarted;
    // getters setters
}