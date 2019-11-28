package com.weather.forcast.utilies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class WeatherRemoteUtilies {
	
	private final Logger logger= LoggerFactory.getLogger(this.getClass());

	@Value("${openweather.APPID}")
	private String appId;
	@Value("${openweather.units}")
	private String defaultUnit;
	
	
	public MultiValueMap<String,String> buildRequestParams (String query){
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.REQUEST_PARAM+Constants.QUERY_PARAM+query+Constants.APPID+appId+Constants.UNITES+defaultUnit);
		MultiValueMap<String, String>weatherParams=new LinkedMultiValueMap<>();
		weatherParams.add("q", query);
		weatherParams.add("APPID", appId);
		weatherParams.add("units", defaultUnit);
		logger.info(Constants.METHOD_RETURN+weatherParams.toString());
		logger.info(Constants.END_METHOD);
		return weatherParams;
	}

}
