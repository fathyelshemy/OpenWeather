package com.unittest.weather.forcast.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.weather.forcast.model.Employee;
import com.weather.forcast.repository.EmployeeRepo;
import com.weather.forcast.service.ProfileService;
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

	@Mock EmployeeRepo empRepo;
	
	@InjectMocks ProfileService profileService;
	
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
		Mockito.when(empRepo.findTopByOrderByIdDesc())
					.thenReturn(Optional.of(emp));
		Mockito.when(empRepo.save(emp)).thenReturn(emp);
		assertTrue(profileService.registerEmployee(emp));
	}

	@Test
	public void testRetrieveEmployeeByMail() {
		Mockito.when(empRepo.findByEmail(Mockito.anyString())).
		thenReturn(Optional.of(emp));
		assertEquals(profileService.retrieveEmployeeByMail("mail@mail.com").getId(), 5L);
	}

}
