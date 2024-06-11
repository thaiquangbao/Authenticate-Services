package com.iuh.users_service.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("addCorsMappings");
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins("*");
            }
        };
    }
}
