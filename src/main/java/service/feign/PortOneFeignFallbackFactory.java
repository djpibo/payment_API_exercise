package service.feign;

import dto.AccessTokenRequestDTO;
import dto.AccessTokenResponseDTO;
import dto.SubscribeRequestDTO;
import dto.SubscribeResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortOneFeignFallbackFactory implements FallbackFactory<PortoneClient> {

    private final PortoneClient portoneClient;

    public PortOneFeignFallbackFactory(PortoneClient portoneClient) {
        this.portoneClient = portoneClient;
    }

    @Override
    public PortoneClient create(Throwable cause) {
        return new PortoneClient() {
            @Override
            public AccessTokenResponseDTO postAccessToken(AccessTokenRequestDTO accessTokenRequestDTO) {
                log.info("error occurred, {}", cause.getMessage());
                return new AccessTokenResponseDTO.Empty;
            }
            @Override
            public SubscribeResponseDTO postSubscribePaymentOnetime(String token, SubscribeRequestDTO subscribeDTO) {
                return new SubscribeResponseDTO("fallback; reason was: " + cause.getMessage());
            }
            @Override
            public Hello iFailSometimes() {
                return new Hello("fallback; reason was: " + cause.getMessage());
            }
        };
    }
}