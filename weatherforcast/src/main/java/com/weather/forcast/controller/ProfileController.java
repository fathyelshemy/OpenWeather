package com.weather.forcast.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forcast.model.Email;
import com.weather.forcast.model.Employee;
import com.weather.forcast.service.ProfileService;
import com.weather.forcast.utilies.Constants;
import com.weather.forcast.utilies.EmployeeHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(value = "profile",description = "used to add or get new user to system",
	consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces ="no thing" )
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class ProfileController {

	private final Logger logger= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeHelper empHelper;
	@Autowired
	private ProfileService profileService;


	
	@ApiOperation(nickname = "registration",notes = "used to add new user",produces = "application/json", value = "registration")
	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+employee.toString());
		boolean saved=profileService.registerEmployee(employee);
		logger.info(Constants.END_METHOD);
		if (saved==true)
			return new ResponseEntity<>("", HttpStatus.CREATED);
		else
			return new ResponseEntity<>("",HttpStatus.CONFLICT);
	}
	
	@ApiOperation(nickname = "profile",notes = "used to retrieve employee profile",
			consumes = "application/json", value = "getProfile",authorizations = {@Authorization(value = "Bearer")})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/profile")
	@ResponseBody
	public ResponseEntity<?>getEmployeeProfile(@RequestBody Email email){
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+email.getEmail());
		Employee emp=profileService.retrieveEmployeeByMail(email.getEmail());
		logger.info(Constants.END_METHOD);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
}
