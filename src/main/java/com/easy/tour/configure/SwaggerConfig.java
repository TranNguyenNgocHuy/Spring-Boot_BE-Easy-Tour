package com.easy.tour.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

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
