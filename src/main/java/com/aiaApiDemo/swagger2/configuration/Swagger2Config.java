package com.aiaApiDemo.swagger2.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	// 該路徑底下所有api會自動暴露
	public static final String BASE_PACKAGE = "com.aiaApiDemo";
	
	// application.properties 注入
	@Value("${swagger.enable}")
	private boolean enableSwagger;
	
	// application.properties 注入
	@Value("${swagger.swaggerUrl}")
	private String swaggerUrl;
	
	@Bean
	public Docket createRestApi() {
		// 自動配置加載 swagger
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()) 
				.enable(enableSwagger) // 依據不同環境，開關 Swagger
				.directModelSubstitute(Timestamp.class, Long.class) // 型別轉換
				.directModelSubstitute(Date.class, Long.class).select() // 掃描的接口路徑包
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		
		// API訊息
		Contact contact = new Contact("Henry.Chang", "", "henry.chang@eBaotech.com");

		return new ApiInfoBuilder()
				.title("AIA Swagger RESTful APIs Demon")
				.description("Swagger API 服務")
				.termsOfServiceUrl("http://swagger.io/")
				.contact(contact)
				.version("1.0")
				.build();
	}

}
