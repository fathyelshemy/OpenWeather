package com.weather.forcast.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.weather.forcast"))              
          .paths(PathSelectors.any())                          
          .build().securitySchemes(Lists.newArrayList(new ApiKey("JWT", "Authorization", "header")))
          .securityContexts(Arrays.asList(securityContext()))
          .apiInfo(WeatherAPIMetaData());                                           
    }
	
	private ApiInfo WeatherAPIMetaData() {
		Contact contact= new Contact();
		contact.setEmail("fathyelshemy8@gmail.com");;
		contact.setName("Weather API Documentation");
		contact.setUrl("http://localhost:8888");
		return new ApiInfoBuilder().title("Weather API Documentation")
				.description("describe current weather for specific city with Bearer auth")
				.contact(contact.toString())
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("0.0.1")
				.build();
		
	}
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
            .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
            "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT",
            authorizationScopes));
        }

}
