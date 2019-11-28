package com.unittest.weather.forcast.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.weather.forcast.model.WeatherNote;
import com.weather.forcast.repository.WeatherNotesRepo;
import com.weather.forcast.service.WeatherNoteService;
import com.weather.forcast.utilies.Constants;

@RunWith(MockitoJUnitRunner.class)
public class WeatherNoteServiceTest {
	@Mock WeatherNotesRepo weatherRepo;
	
	@InjectMocks WeatherNoteService weatherService;
	
	WeatherNote weatherNote;
	@Before
	public void setUp() throws Exception {
		this.weatherNote= new WeatherNote();
		weatherNote.setCity("Cairo");
		weatherNote.setId(20);
		weatherNote.setMaxTemp(18);
		weatherNote.setMinTemp(17.78);
		weatherNote.setNote("it's fine for parking");
		weatherNote.setTemp(17.88);
	}

	@Test
	public void testGetTodayNoteByCity() {
		
		Mockito.when(weatherRepo.findTodayWeatherNotesByCity(Mockito.anyString()))
		.thenReturn(this.weatherNote);

		assertThat(weatherService.getTodayNoteByCity("Cairo")).isNotNull();
		
	}

	@Test
	public void testGetHistoricNotesByCity() {
		Mockito.when(weatherRepo.findWeatherNotesByCity(Mockito.anyString()))
		.thenReturn(Stream.of(weatherNote,weatherNote).collect(Collectors.toList()));
		
		assertThat(weatherService.getHistoricNotesByCity("Ciaro").size()).isEqualTo(2);
	}

	@Test
	public void testSetDefaultNote() {
		assertEquals(weatherService.setDefaultNote(12), Constants.DEGREE_BETWEEN_10_AND_15);
		assertEquals(weatherService.setDefaultNote(9), Constants.DEGREE_LESS_THAN_10);
		assertEquals(weatherService.setDefaultNote(17), Constants.DEGREE_BETWEEN_15_AND_20);
		assertEquals(weatherService.setDefaultNote(30), Constants.DEGREE_MORE_THAN_20);
	}

	@Test
	public void testGetOldWeatherNotes() {
		Mockito.when(weatherRepo.findOldWeatherNotes())
		.thenReturn(Stream.of(weatherNote,weatherNote).collect(Collectors.toList()));
		assertThat(weatherService.getOldWeatherNotes().size()).isEqualTo(2);
	}

	@Test
	public void testUpdateWeatherNote() {
		Mockito.when(weatherRepo.save(Mockito.any()))
		.thenReturn(this.weatherNote);
		assertThat(weatherService.updateWeatherNote(this.weatherNote)).isNotNull();

	}

}
