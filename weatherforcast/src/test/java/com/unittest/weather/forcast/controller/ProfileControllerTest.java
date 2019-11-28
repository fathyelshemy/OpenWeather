package com.unittest.weather.forcast.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weather.forcast.controller.ProfileController;
import com.weather.forcast.model.Email;
import com.weather.forcast.model.Employee;
import com.weather.forcast.service.ProfileService;

@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
	
	@Mock ProfileService profileService;
	
	@InjectMocks ProfileController profileController;
	
	Employee emp;

	@Before
	public void setUp() throws Exception {
		this.emp= new Employee();
		this.emp.setId(5);
		this.emp.setUsername("fathyAhmed");
		this.emp.setEmail("fathy@ahmed.com");
		this.emp.setPassword("12345678");
		this.emp.setMobileNumber("01090880128");
		
	}

	@Test
	public void testRegisterEmployee() {
		when(profileService.registerEmployee(emp)).thenReturn(true);
		MockHttpServletRequest request= new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<?>response=profileController.registerEmployee(emp);
		assertThat(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void testGetEmployeeProfile() {
		when(profileService.retrieveEmployeeByMail(Mockito.anyString())).thenReturn(emp);
		MockHttpServletRequest request= new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Email email=new Email();
		email.setEmail("mail@mail.com");
		ResponseEntity<?>response=profileController.getEmployeeProfile(email);
		assertThat(response.getStatusCode().is2xxSuccessful());
	}

}
