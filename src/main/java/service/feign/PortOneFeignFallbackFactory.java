package service.feign;

import dto.AccessTokenRequestDTO;
import dto.AccessTokenResponseDTO;
import dto.PortonePayRequestDTO;
import dto.PortonePayResponseDTO;
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
                return null;
            }
            @Override
            public PortonePayResponseDTO postSubscribePaymentOnetime(String token, PortonePayRequestDTO subscribeDTO) {
                return null;
            }
            @Override
            public Hello iFailSometimes() {
                return new Hello("fallback; reason was: " + cause.getMessage());
            }
        };
    }
}