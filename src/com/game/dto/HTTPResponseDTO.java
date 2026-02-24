package com.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HTTPResponseDTO {
    private String message;
    private int statusCode;
}
