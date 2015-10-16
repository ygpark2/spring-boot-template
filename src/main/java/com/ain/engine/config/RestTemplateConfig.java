package com.ain.engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name="csosRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}