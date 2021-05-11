package com.shrinq.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
	@Bean
	public Docket api() {
		
		  List<SecurityScheme> kes = new ArrayList<SecurityScheme>();
		  kes.add(apiKey());
		  
		  List<SecurityContext> bes = new ArrayList<SecurityContext>();
		  bes.add(securityContext());
		 
		  return new Docket(DocumentationType.SWAGGER_2).select()
		            .apis(RequestHandlerSelectors.any())
		            .paths(PathSelectors.any())
		            .build()
		            .securitySchemes(kes)
		            .securityContexts(bes)
		            .apiInfo(apiEndPointsInfo());
	}

	@Bean
	SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();

	}

	List<SecurityReference> defaultAuth() {

		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> bes = new ArrayList<SecurityReference>();
		bes.add(new SecurityReference("JWT", authorizationScopes));
		return bes;
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API").description("DUE DILIGENCE REST API")
				.contact(new Contact("Bryan Laryea", "www.dlconsult.com", "bry_lar@yahoo.com")).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
	}
}
