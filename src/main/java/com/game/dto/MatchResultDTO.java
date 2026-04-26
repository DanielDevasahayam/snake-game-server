package com.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchResultDTO {
    private String playerId;
    private String opponent;
    private String roomId;
    private String type;
    private String roomid;
}
