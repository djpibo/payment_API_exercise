package dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenResponseDTO {
    private int code;
    private String message;
    private AuthAnnotation response;

    @Data
    public static class AuthAnnotation{
        private int now;
        private String accessToken;
        private int expiredAt;
    }
}
