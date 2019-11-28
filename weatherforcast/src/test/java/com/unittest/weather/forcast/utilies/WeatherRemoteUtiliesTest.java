/**
 * 
 */
package com.unittest.weather.forcast.utilies;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.forcast.utilies.WeatherRemoteUtilies;

/**
 * @author fathyelshemy
 *
 */
@RunWith(SpringRunner.class)
public class WeatherRemoteUtiliesTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildRequestParams() {
		assertEquals(new WeatherRemoteUtilies().buildRequestParams("Cairo").get("q").get(0), "Cairo");
		
	}

}
