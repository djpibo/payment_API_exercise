package dto;

import lombok.Data;
import lombok.Getter;

@Data
public class AccessTokenResponseDTO {
    private int code;
    private String message;
    private AuthAnnotation response;

    @Data
    public class AuthAnnotation{
        private int now;
        private String accessToken;
        private int expiredAt;
    }
}
