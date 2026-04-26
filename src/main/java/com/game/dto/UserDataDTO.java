package com.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDataDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
}
