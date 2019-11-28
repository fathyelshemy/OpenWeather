package com.unittest.weather.forcast.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.weather.forcast.service.WeatherRemoteService;
import com.weather.forcast.utilies.CustomResttemplate;
import com.weather.forcast.utilies.WeatherRemoteUtilies;

@RunWith(MockitoJUnitRunner.class)
public class WeatherRemoteServiceTest {
	
	@Mock CustomResttemplate restTemplate;
	
	@Mock WeatherRemoteUtilies weatherUtilies;
	
	@InjectMocks WeatherRemoteService weatherRemote;

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRetrieveWeather() {
				
		MultiValueMap<String, String>multiValue=new LinkedMultiValueMap<>();
		RestTemplate objRestTemplate=mock(RestTemplate.class);
		UriComponentsBuilder uri= mock(UriComponentsBuilder.class);
		HttpEntity<?>requestentity=mock(HttpEntity.class);
		Mockito.when(weatherUtilies.buildRequestParams(Mockito.anyString()))
						.thenReturn(multiValue);
		doReturn(objRestTemplate).when(restTemplate).buildRestTemplate();
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("sampleBodyString", HttpStatus.OK);
//		when(objRestTemplate.exchange(	Mockito.anyString(), HttpMethod.GET, requestentity, String.class)).thenReturn(responseEntity);
		doReturn(responseEntity).when(objRestTemplate).exchange(uri.toUriString(), HttpMethod.GET, requestentity, String.class);
		assertEquals(weatherRemote.retrieveWeather("ciaro").getStatusCode(), HttpStatus.OK);
	}

}
