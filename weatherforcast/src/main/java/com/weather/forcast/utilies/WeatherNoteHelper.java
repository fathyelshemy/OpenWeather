package com.weather.forcast.utilies;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weather.forcast.model.WeatherNote;
import com.weather.forcast.service.WeatherNoteService;

@Component
public class WeatherNoteHelper {
	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WeatherNoteService weatherService;
	
	public WeatherNote buildWeatherNote(String weatherServiceResponse  ) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+weatherServiceResponse);

		JSONObject response= new JSONObject(weatherServiceResponse);
		WeatherNote weatherNote= new WeatherNote();
		weatherNote.setCity(response.getString("name"));
		weatherNote.setDate(new Date());
		weatherNote.setTemp(response.getJSONObject("main").getDouble("temp"));
		weatherNote.setMinTemp(response.getJSONObject("main").getDouble("temp_min"));
		weatherNote.setMaxTemp(response.getJSONObject("main").getDouble("temp_max"));
		weatherNote.setNote(weatherService.setDefaultNote(weatherNote.getTemp()));
		logger.info(Constants.METHOD_RETURN+weatherNote.toString());
		logger.info(Constants.END_METHOD);

		return weatherNote;
		
	}

}
