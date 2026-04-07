package com.game.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
public class GameQueueDTO {
    private String userId;
    private String status;
}
