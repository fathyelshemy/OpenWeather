package com.unittest.weather.forcast.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weather.forcast.controller.WeatherController;
import com.weather.forcast.model.WeatherNote;
import com.weather.forcast.service.WeatherNoteService;
import com.weather.forcast.service.WeatherRemoteService;
import com.weather.forcast.utilies.WeatherNoteHelper;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

	@Mock WeatherRemoteService remoteService;
	
	@Mock WeatherNoteHelper weatherHelper;
	
	@Mock WeatherNoteService weatherService;
	
	@InjectMocks WeatherController weatherController;
	WeatherNote weather;
	@Before
	public void setUp() throws Exception {
		this.weather= new WeatherNote();
		this.weather.setId(1L);
		this.weather.setCity("Cairo");
		this.weather.setDate(new Date());
		this.weather.setMaxTemp(23.9);
		this.weather.setMinTemp(10.1);
		this.weather.setNote("note");
	}

	@Test
	public void testRetrieveWeatherByCity() {

		
		when(remoteService.retrieveWeather(Mockito.anyString()))
					.thenReturn(new ResponseEntity("",HttpStatus.OK));
		
		when(weatherHelper.buildWeatherNote(Mockito.anyString()))
					.thenReturn(weather);
		
		when(weatherService.saveWeatherNote(weather)).thenReturn(true);
		
		MockHttpServletRequest request= new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<?>response=weatherController.retrieveWeatherByCity("Cairo");
		assertThat(response.getStatusCode().is2xxSuccessful());
		
	}

	@Test
	public void testGetOldWeatherNote() {
		when(weatherService.getOldWeatherNotes())
				.thenReturn(Stream.of(new WeatherNote(),new WeatherNote()).collect(Collectors.toList()));
		MockHttpServletRequest request= new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<?>response=weatherController.getOldWeatherNote();
		assertThat(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void testUpdateWeatherNote() {
		when(weatherService.updateWeatherNote(weather)).thenReturn(weather);
		MockHttpServletRequest request= new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<?>response=weatherController.updateWeatherNote(weather);
		assertThat(response.getStatusCode().is2xxSuccessful());

	}

}
