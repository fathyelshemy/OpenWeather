package com.weather.forcast.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.weather.forcast.utilies.Constants;

@Component
public class RestLoggerService {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	public void logRequest(HttpServletRequest request,Object body) {
		
		logger.info(Constants.INCOMING_REQUEST);
		logger.info("EndPoint: {}", request.getServerName().toString()+":"+request.getServerPort()+
				request.getRequestURI().toString());
		logger.info("method: {}", request.getMethod());
		if(body!=null) {
			logger.info("requestBody: {}", body.toString());
		}
		
		logger.info(Constants.END_METHOD);

	}
	
	public void logResponse(HttpServletResponse response,Object body) {
		logger.info(Constants.OUTGOING_RESPONSE);
		logger.info("code: {} ",response.getStatus());
		if(body!=null) {
			logger.info("requestBody: {}", body.toString());
		}
		logger.info(Constants.END_METHOD);

	}

}
