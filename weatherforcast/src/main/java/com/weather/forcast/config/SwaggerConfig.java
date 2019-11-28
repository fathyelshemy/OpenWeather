package com.weather.forcast.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
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
          .build().securitySchemes(Collections.singletonList(new ApiKey("Bearer ", "Authorization", "header"))).apiInfo(WeatherAPIMetaData());                                           
    }
	
	private ApiInfo WeatherAPIMetaData() {
		Contact contact= new Contact();
		contact.setEmail("fathyelshemy8@gmail.com");;
		contact.setName("Weather API Documentation");
		contact.setUrl("http://localhost:8888");
		return new ApiInfo("Weather API Documentation", "discribe current weather for specific city", "0.0.1",
				"Apache", "Weather Trail", "APache", "");
		
	}
	

}
