package com.blog.blogapp.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKey()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> contexts()
	{
		return java.util.Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	private List<SecurityReference> securityReferences()
	{
		AuthorizationScope scope = new AuthorizationScope("globel", "Access Eveerything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{scope}));
	}
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(contexts())
				.securitySchemes(java.util.Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging App", "This is a blogging app", "1.0", "termas of service",
				new Contact("nikhil SIngh", "", "nikhil@gmail.com"), "Licence of upi", "Api Licence Url",
				Collections.EMPTY_LIST);
	}
}
