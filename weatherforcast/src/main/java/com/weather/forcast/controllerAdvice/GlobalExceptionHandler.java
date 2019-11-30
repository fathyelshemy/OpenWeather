package com.weather.forcast.controllerAdvice;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weather.forcast.utilies.Constants;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handle(Exception exception) {
    // general exception
    LOG.error(Constants.GENERIC_ERROR_MESSAGE, exception);
    
    Map<String, String> response =new HashMap<>();
    response.put("Time", new Date().toString());
    if(exception instanceof IllegalArgumentException) {
    	response.put(Constants.message, Constants.NOT_FOUND_JWT_TOKEN);
    	
    }else if(exception instanceof ExpiredJwtException) {
    	response.put(Constants.message, Constants.JWT_TOKEN_EXPIRED);
    }else if(exception instanceof UsernameNotFoundException || exception instanceof  BadCredentialsException) {
    	response.put(Constants.message, Constants.USER_NOT_FOUND);
    }else {
    	response.put(Constants.message, Constants.GENERIC_ERROR_MESSAGE);
    }
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}