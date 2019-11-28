package com.weather.forcast.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weather.forcast.model.WeatherNote;

@Transactional
@Repository
public interface WeatherNotesRepo extends JpaRepository<WeatherNote, Long> {
	
	@Query(nativeQuery = true,value = "select * from weather_note where date= CURDATE() and city= :city ; ")  //,@Param("today")LocalDate today
	public WeatherNote findTodayWeatherNotesByCity(@Param("city")String city);
	
	@Query(nativeQuery = true,value = "select * from weather_note where city= :city ;")
	public List<WeatherNote> findWeatherNotesByCity(@Param("city")String city);
	
	@Query(nativeQuery = true,value = "select * from weather_note ; ")
	public List<WeatherNote>findOldWeatherNotes();
	
	public WeatherNote findTopByOrderByIdDesc();


}
