package com.example.taskproject.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortalApiConfig {
  @Bean
  @ConditionalOnProperty(prefix = "task.com", name = "portal", havingValue = "PortOne")
  public PortalApi PortOneApi() {
    return new PortoneApi();
  }

}
