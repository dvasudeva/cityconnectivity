package com.mastercard.cityconnectivity.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select().paths(PathSelectors.ant("/connected"))
				.apis(RequestHandlerSelectors.basePackage("com.mastercard"))
				.build().apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo(){
		return new ApiInfo("City Connectivity API", 
				"API to lookup if 2 cities are connected directly or indiretcly",
				"1.0", "", 
				new Contact("Davinder Vasudeva", "", "dvasudeva.java@gmail.com"), 
				"", 
				"", Collections.EMPTY_LIST);
	}
}
