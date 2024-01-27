package service.feign;

import config.FeignConfig;
import dto.AccessTokenRequestDTO;
import dto.AccessTokenResponseDTO;
import dto.PortonePayRequestDTO;
import dto.PortonePayResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FeignClient", url = "https://api.iamport.kr", configuration = FeignConfig.class)
public interface PortoneClient {

    @RequestMapping(method = RequestMethod.POST, value = "/users/getToken")
    AccessTokenResponseDTO postAccessToken(@RequestParam AccessTokenRequestDTO accessTokenRequestDTO);
    @RequestMapping(method = RequestMethod.POST, value = "/subscribe/payments/onetime")
    PortonePayResponseDTO postSubscribePaymentOnetime(@RequestHeader("Authorization") String token, @RequestParam PortonePayRequestDTO subscribeDTO);

    Hello iFailSometimes();
}