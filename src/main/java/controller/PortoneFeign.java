package controller;

import config.FeignConfig;
import dto.AccessTokenDTO;
import dto.SubscribeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "FeignClient", url = "https://api.iamport.kr", configuration = FeignConfig.class)
public interface PortoneFeign {

    @RequestMapping(method = RequestMethod.POST)
    List<AccessTokenDTO> postAccessToken(@RequestParam(value="imp_key") String key, @RequestParam(value="imp_secret") String secret);

    @RequestMapping(method = RequestMethod.POST)
    List<AccessTokenDTO> postSubscribePaymentOnetime(@RequestParam SubscribeDTO subscribeDTO);
}