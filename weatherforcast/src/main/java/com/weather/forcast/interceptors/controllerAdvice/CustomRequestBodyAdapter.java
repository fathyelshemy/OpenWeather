package com.weather.forcast.interceptors.controllerAdvice;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.weather.forcast.service.RestLoggerService;

@ControllerAdvice
public class CustomRequestBodyAdapter extends RequestBodyAdviceAdapter {

	@Autowired
	private RestLoggerService loggerService;
	
	 @Autowired
	    HttpServletRequest request;
	
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}
	
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		loggerService.logRequest((HttpServletRequest)request, body);
		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}

}
