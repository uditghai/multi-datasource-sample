package com.sample.project.sample.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Setter
@Configuration
@EnableSwagger2
@ConfigurationProperties("app.api")
@ConditionalOnProperty(name = "app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {

	private String version;
	private String title;
	private String description;
	private String basePackage;

	@Bean
	public Docket api() {
		log.info("basePackage: {}", basePackage);
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any()).build().directModelSubstitute(LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(LocalDateTime.class, java.util.Date.class).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title).description(description).version(version).build();
	}
}
