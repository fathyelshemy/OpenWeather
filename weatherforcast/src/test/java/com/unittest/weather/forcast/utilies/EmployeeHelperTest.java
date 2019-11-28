package com.unittest.weather.forcast.utilies;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.forcast.model.Employee;
import com.weather.forcast.utilies.EmployeeHelper;

@RunWith(SpringRunner.class)
public class EmployeeHelperTest {

	Employee emp;
	@Before
	public void setUp() throws Exception {
		this.emp= new Employee();
		emp.setUsername("fathyAhmed");
		emp.setEmail("fathy@ahmed.com");
		emp.setPassword("12345678");
		emp.setMobileNumber("01090880128");
	}

	//{	  'username':'fathyAhmed', 'email':'fathy@ahmed.com', 'password':'12345678',  'mobileNumber':'01090880128'}
	@Test
	public void testGetEmployee() {

		
		EmployeeHelper empHelper=new EmployeeHelper();
		Employee generatedEmp=empHelper.getEmployee("{ 'username':'fathyAhmed', 'email':'fathy@ahmed.com', 'password':'12345678',  'mobileNumber':'01090880128'}");
		assertEquals(emp.getEmail(),generatedEmp.getEmail());
		assertEquals(emp.getMobileNumber(),generatedEmp.getMobileNumber());
		assertEquals(emp.getUsername(),generatedEmp.getUsername());
		assertEquals(emp.getPassword(),generatedEmp.getPassword());
		
	}

	// {  'email': 'string' }
	@Test
	public void testGetEmail() {
		EmployeeHelper empHelper=new EmployeeHelper();
		String email=empHelper.getEmail("{ 'email': 'fathy@ahmed.com' }");
		assertEquals(email,"fathy@ahmed.com");
		
	}

}
