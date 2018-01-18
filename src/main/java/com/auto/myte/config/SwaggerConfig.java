package com.auto.myte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo initApiInfo() {
		return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("auto myte XXXXXXXXXXXX")
                .termsOfServiceUrl("http://ip：8089/myte")
                .contact("chunhui.li")
                .version("1.0")
                .build();
	}
	
	@Bean
	public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(initApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.auto.myte.controller"))
                .paths(PathSelectors.any())
                .build();
	}
}
