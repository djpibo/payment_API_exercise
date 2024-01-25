package config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import interceptor.FeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor feignInterceptor() {
        String token = "token";
        return new FeignInterceptor(token);
    }

}
