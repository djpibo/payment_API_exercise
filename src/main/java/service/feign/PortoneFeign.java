package service.feign;

import config.FeignConfig;
import dto.AccessTokenRequestDTO;
import dto.AccessTokenResponseDTO;
import dto.SubscribeRequestDTO;
import dto.SubscribeResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "FeignClient", url = "https://api.iamport.kr", configuration = FeignConfig.class)
public interface PortoneFeign {

    @RequestMapping(method = RequestMethod.POST, value = "/users/getToken")
    AccessTokenResponseDTO postAccessToken(@RequestParam AccessTokenRequestDTO accessTokenRequestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/subscribe/payments/onetime")
    SubscribeResponseDTO postSubscribePaymentOnetime(@RequestParam SubscribeRequestDTO subscribeDTO);
}