package com.weather.forcast.utilies;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.weather.forcast.model.Employee;

@Component
public class EmployeeHelper {
	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	public Employee getEmployee(String employeeStr) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+employeeStr);

		JSONObject employeeJson= new JSONObject(employeeStr);
		Employee employee= new Employee();
		employee.setEmail(employeeJson.getString("email"));
		employee.setUsername(employeeJson.getString("username"));
		employee.setMobileNumber(employeeJson.getString("mobileNumber"));
		employee.setPassword(employeeJson.getString("password"));
		logger.info(Constants.METHOD_RETURN+employee.toString());
		logger.info(Constants.END_METHOD);

		return employee;
	}
	
	public String getEmail(String emailStr) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+emailStr);

		JSONObject json= new JSONObject(emailStr);
		String email=json.getString("email");
		
		logger.info(Constants.METHOD_RETURN+email);
		logger.info(Constants.END_METHOD);

		return email;

	}
}
