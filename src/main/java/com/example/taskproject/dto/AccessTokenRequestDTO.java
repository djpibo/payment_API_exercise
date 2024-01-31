package com.example.taskproject.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenRequestDTO {
    private String impKey;
    private String impSecret;

    public static AccessTokenRequestDTO of(String imp_key, String imp_secret) {
        AccessTokenRequestDTO requestDTO = new AccessTokenRequestDTO();
        requestDTO.impKey = imp_key;
        requestDTO.impSecret = imp_secret;
        return requestDTO;
    }
}
