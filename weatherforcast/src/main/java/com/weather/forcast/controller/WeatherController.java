package com.weather.forcast.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forcast.model.WeatherNote;
import com.weather.forcast.service.WeatherNoteService;
import com.weather.forcast.service.WeatherRemoteService;
import com.weather.forcast.utilies.Constants;
import com.weather.forcast.utilies.WeatherNoteHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@Api(value = "get weather conditions", consumes = "application/json")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WeatherRemoteService weatherService;
	@Autowired
	private WeatherNoteHelper weatherHelper;
	@Autowired
	private WeatherNoteService weatherNoteService;

	@ApiOperation(nickname = "get specific city Weather",notes = "used to specific city weather with defautl value Cairo",
			produces = "application/json", value = "getCityWeather",authorizations = {@Authorization(value = "Bearer")})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(path = "/{city}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> retrieveWeatherByCity(@PathVariable String city) {
		logger.info(Constants.CLASS_NAME + this.getClass().getName() + Constants.METHOD_NAME + new Object(){ }.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS + city);
//		WeatherNote localWeather = weatherNoteService.getTodayNoteByCity(city);
		WeatherNote weatherNote=null;
//		if (localWeather == null) {
			String weatherResponse = weatherService.retrieveWeather(city).getBody().toString();
			 weatherNote = weatherHelper.buildWeatherNote(weatherResponse);
			weatherNoteService.saveWeatherNote(weatherNote);
//		}else {
//			weatherNote=localWeather;
//			logger.info(Constants.LOCAL_WEATHER_NOTE+weatherNote.toString());
//		}
		
		logger.info(Constants.END_METHOD);
		return new ResponseEntity(weatherNote, HttpStatus.OK);
	}
    
    
	@ApiOperation(nickname = "get all all Weather",notes = "used to get all old  weather with every city and notes for every one"
			,produces = "application/json", value = "getAllWeathersNote",authorizations = {@Authorization(value = "Bearer")})

    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/oldNotes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getOldWeatherNote(){
		logger.info(Constants.CLASS_NAME + this.getClass().getName() + Constants.METHOD_NAME + new Object(){ }.getClass().getEnclosingMethod().getName());

		List<WeatherNote> notes=weatherNoteService.getOldWeatherNotes();
		
		logger.info(Constants.END_METHOD);
		return new ResponseEntity(notes,HttpStatus.OK);
	}

	
	@ApiOperation(nickname = "edit specific city Weather",notes = "used to edit specific city weather with",
			produces = "application/json", value = "editWeatherNote",authorizations = {@Authorization(value = "Bearer")})
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/editNote", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateWeatherNote(@RequestBody WeatherNote weatherNote){
		logger.info(Constants.CLASS_NAME + this.getClass().getName() + Constants.METHOD_NAME + new Object(){ }.getClass().getEnclosingMethod().getName());

		WeatherNote updateNote=weatherNoteService.updateWeatherNote(weatherNote);
		logger.info(Constants.END_METHOD);
		return new ResponseEntity(updateNote,HttpStatus.CREATED);
	}
}
