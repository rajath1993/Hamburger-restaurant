package com.hamburger.administration;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HamburgerAdministrationApplication {
	
	private static final Logger logger = LogManager.getLogger(HamburgerAdministrationApplication.class.getName());
	public static void main(String[] args) {
		logger.info("Application started running");
		SpringApplication.run(HamburgerAdministrationApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.hamburger.administration.controller"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Hamburger restaurant admin API",
				"Represents CRUD operations",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Rajath Anand", "https://rajathanand.netlify.app/", "anand.rajath@gmail.com"),
				"API License",
				"http://google.com",
				Collections.emptyList());
	}

}
