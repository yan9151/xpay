package com.xpay.admin.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lx.framework.utils.JsonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class AdminConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminConsoleApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtils.newObjectMapper();
    }
}
