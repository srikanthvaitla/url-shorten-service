package com.xpanse.www.urlshorten.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("urlShortenService", "URL Shorten Service"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("URL Shorten Service")
                .description("URL Shorten service is a REST API, deals with shorten the URL")
                .version("1.0")
                .build();
    }

}
