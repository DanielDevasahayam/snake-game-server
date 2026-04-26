package com.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataDTO {

    private int bodyParts;

    private int score;

    private int[] applePositions;

    private boolean gameOver;

    private int[] applePosX;

    private int[] applePosY;

}
