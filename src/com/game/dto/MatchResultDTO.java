package com.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResultDTO {
    private String playerId;

    private String opponent;
    private String roomId;


}
