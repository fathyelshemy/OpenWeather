package com.weather.forcast.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.weather.forcast.utilies.Constants;
import com.weather.forcast.utilies.CustomResttemplate;
import com.weather.forcast.utilies.WeatherRemoteUtilies;

@Service
public class WeatherRemoteService {
	
	private final Logger logger= LoggerFactory.getLogger(this.getClass());

	@Value("${openweather.url}")
	private String url;

	@Autowired
	private WeatherRemoteUtilies weatherUtilies;
	
	@Autowired
	private CustomResttemplate customRestTemplate;
	
	public ResponseEntity<?> retrieveWeather(String city) {
		
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+city);
		RestTemplate restRequest= customRestTemplate.buildRestTemplate();
		HttpHeaders headers= new HttpHeaders();
		headers.set("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
		UriComponentsBuilder uri= UriComponentsBuilder.fromUriString(url).
				path("/data/2.5/weather")
				.queryParams(weatherUtilies.buildRequestParams(city));
		HttpEntity<String>entity= new HttpEntity<>(headers);
		ResponseEntity<String>WeatherResponse=restRequest.exchange(uri.toUriString(), HttpMethod.GET, entity, String.class);
		logger.info(Constants.END_METHOD);
		return WeatherResponse;
	}
	
	

}
