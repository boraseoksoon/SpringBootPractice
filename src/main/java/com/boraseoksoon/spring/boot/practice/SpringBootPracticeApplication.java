package com.boraseoksoon.spring.boot.practice;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class SpringBootPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.boraseoksoon.spring.boot.practice.controller"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"My REST API",
				"Some custom description of API.",
				"API TOS",
				"Terms of service",
				"myeaddress@company.com",
				"License of API",
				"API license URL");
		return apiInfo;
	}
}
