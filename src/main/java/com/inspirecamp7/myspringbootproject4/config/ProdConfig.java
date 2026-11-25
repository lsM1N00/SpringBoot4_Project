package com.inspirecamp7.myspringbootproject4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdConfig {
    @Bean
    public CustomVO customVO(){
        return CustomVO.builder()
                .mode("운영 Mode")
                .rate(1.9)
                .build();
    }
}
