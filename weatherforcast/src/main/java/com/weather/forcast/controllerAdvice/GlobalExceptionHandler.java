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


  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Map<String, String>> handle(Exception exception) {
    
    LOG.error(Constants.GENERIC_ERROR_MESSAGE, exception);
    
    Map<String, String> response =new HashMap<>();
    response.put("Time", new Date().toString());
    if(exception.getCause() instanceof IllegalArgumentException) {
    	response.put(Constants.message, Constants.NOT_FOUND_JWT_TOKEN);
    	response.put("Status", HttpStatus.BAD_REQUEST.toString());
    	
    }else if(exception.getCause() instanceof ExpiredJwtException) {
    	response.put(Constants.message, Constants.JWT_TOKEN_EXPIRED);
    	response.put("Status", HttpStatus.BAD_REQUEST.toString());
    }else if(exception.getCause() instanceof UsernameNotFoundException || exception.getCause() instanceof  BadCredentialsException) {
    	response.put(Constants.message, Constants.USER_NOT_FOUND);
    	response.put("Status", HttpStatus.BAD_REQUEST.toString());
    }else {
    	response.put(Constants.message, Constants.GENERIC_ERROR_MESSAGE);
    	response.put("Status", HttpStatus.BAD_REQUEST.toString());
    }
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}