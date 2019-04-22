package com.stackroute.musemanager.config;

import org.springframework.context.annotation.Bean;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {

	public SwaggerConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.musemanager")).paths(regex("/api/jobservice.*")).build();

	}

}
