package com.weather.forcast.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.forcast.model.WeatherNote;
import com.weather.forcast.repository.WeatherNotesRepo;
import com.weather.forcast.utilies.Constants;

@Service
public class WeatherNoteService {
	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WeatherNotesRepo notesRepo;
	
	public boolean saveWeatherNote(WeatherNote weatherNote) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+weatherNote.toString());

		long prevId=notesRepo.findTopByOrderByIdDesc().getId();
		long id=(prevId==0)?1:prevId+1;
		weatherNote.setId(id);
		if(weatherNote.getNote()==null)
			setDefaultNote(weatherNote.getTemp());
		notesRepo.save(weatherNote);
		logger.info(Constants.END_METHOD);
		return true;
	}
	
	public WeatherNote getTodayNoteByCity(String city) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+city);

		WeatherNote weatherNotes=notesRepo.findTodayWeatherNotesByCity(city);
		logger.info(Constants.END_METHOD);
		return weatherNotes;
	}
	
	public List<WeatherNote> getHistoricNotesByCity(String city){
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+city);

		List<WeatherNote> notes=notesRepo.findWeatherNotesByCity(city);
		logger.info(Constants.END_METHOD);
		return notes;
	}
	
	
	public  String setDefaultNote(double degree) {
		String defaultNote=null;
		if(degree>1 && degree<=10)
			defaultNote=Constants.DEGREE_LESS_THAN_10;
		else if(degree>10 && degree<=15)
			defaultNote=Constants.DEGREE_BETWEEN_10_AND_15;
		else if(degree>15 &&degree<=20)
			defaultNote=Constants.DEGREE_BETWEEN_15_AND_20;
		else
			defaultNote=Constants.DEGREE_MORE_THAN_20;
		return defaultNote;
	}


	public List<WeatherNote>getOldWeatherNotes(){
		List<WeatherNote> notes=notesRepo.findOldWeatherNotes();
		return notes;
	}
	
	public WeatherNote updateWeatherNote(WeatherNote weatherNote) {
		
		return notesRepo.save(weatherNote);
	}
}
