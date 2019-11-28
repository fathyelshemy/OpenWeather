package com.weather.forcast.utilies;

import java.util.Collections;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.forcast.interceptors.RestTemplateInterceptor;

@Component
public class CustomResttemplate {

	public RestTemplate buildRestTemplate() {
		SimpleClientHttpRequestFactory simpleFactory= new SimpleClientHttpRequestFactory();
		simpleFactory.setOutputStreaming(false);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);
		
		RestTemplate restTemplate= new RestTemplate(factory);
		restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));

		return restTemplate;
	}
}
