package com.weather.forcast.interceptors;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.weather.forcast.service.RestLoggerService;



@Component
public class InterceptorsLogging extends HandlerInterceptorAdapter {
	
	
	@Autowired
	private RestLoggerService loggerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
			 loggerService.logRequest(request,null);
		 }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {


	}
	
	
}
