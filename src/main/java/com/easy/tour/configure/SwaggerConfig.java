package com.easy.tour.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    /**
     * Swagger URL: http://localhost:8080/swagger-ui/index.html#/
     * */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
