package com.self.portfolio.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConf implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
                .exposedHeaders("Access-Control-Allow-Origin");
    }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    // registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.self.portfolio")).build().apiInfo((apiInfo()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Portfolio", "Nixon's Portfolio", "1.0", "Free",
                new Contact("Nixon Mathew", "www.solvezy.com", "nixonnelson13@gmail.com"), "API License",
                "iammathews@heroku.com", Collections.emptyList());
    }
}