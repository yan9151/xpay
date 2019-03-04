package com.xpay.admin.console.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean(value = "notify")
    public RestTemplate restTemplateToNotify() {
        return new RestTemplate();
    }
}
