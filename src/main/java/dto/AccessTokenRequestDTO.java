package dto;

import lombok.Data;

@Data
public class AccessTokenRequestDTO {
    private String imp_key;
    private String imp_secret;
    public static AccessTokenRequestDTO of(String imp_key, String imp_secret) {
        AccessTokenRequestDTO requestDTO = new AccessTokenRequestDTO();
        requestDTO.imp_key = imp_key;
        requestDTO.imp_secret = imp_secret;
        return requestDTO;
    }
}
