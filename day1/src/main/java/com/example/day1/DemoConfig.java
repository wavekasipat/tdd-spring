package com.example.day1;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {

  @Bean
  public RestTemplate confRestTemplate() {
    return new RestTemplateBuilder().build();
  }
}
