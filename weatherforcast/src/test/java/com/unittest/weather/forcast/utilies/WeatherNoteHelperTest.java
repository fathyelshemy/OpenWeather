package com.unittest.weather.forcast.utilies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.weather.forcast.service.WeatherNoteService;
import com.weather.forcast.utilies.Constants;
import com.weather.forcast.utilies.WeatherNoteHelper;

@RunWith(MockitoJUnitRunner.class)
public class WeatherNoteHelperTest {
	
	@Mock WeatherNoteService weatherService;
	
	@InjectMocks WeatherNoteHelper weatherHelper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildWeatherNote() {
		Mockito.when(weatherService.setDefaultNote(Mockito.anyDouble())).
		thenReturn(Constants.DEGREE_MORE_THAN_20);
		String MockRemoteResult="{'coord':{'lon':31.24,'lat':30.05},'weather':[{'id':800,'main':'Clear','description':'clear sky','icon':'01n'}],'base':'stations','main':{'temp':18.94,'pressure':1016,'humidity':55,'temp_min':18.89,'temp_max':19},'visibility':10000,'wind':{'speed':3.1,'deg':80},'clouds':{'all':0},'dt':1574627321,'sys':{'type':1,'id':2514,'country':'EG','sunrise':1574569649,'sunset':1574607365},'timezone':7200,'id':360630,'name':'Cairo','cod':200}";
		assertEquals(weatherHelper.buildWeatherNote(MockRemoteResult).getCity(), "Cairo");
		assertEquals(weatherHelper.buildWeatherNote(MockRemoteResult).getMaxTemp(), 19.0,0);
		assertEquals(weatherHelper.buildWeatherNote(MockRemoteResult).getMinTemp(), 18.89,0);
		assertEquals(weatherHelper.buildWeatherNote(MockRemoteResult).getTemp(), 18.94,0);
		assertEquals(weatherHelper.buildWeatherNote(MockRemoteResult).getNote(), Constants.DEGREE_MORE_THAN_20);
	}

}
