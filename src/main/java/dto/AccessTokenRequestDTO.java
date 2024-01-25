package dto;

import lombok.Builder;
import lombok.Data;

@Data
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
