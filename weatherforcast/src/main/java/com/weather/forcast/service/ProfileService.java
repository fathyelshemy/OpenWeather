package com.weather.forcast.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.forcast.model.Employee;
import com.weather.forcast.repository.EmployeeRepo;
import com.weather.forcast.utilies.Constants;

@Service
public class ProfileService {
	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepo empRepo;
	
	public boolean registerEmployee(Employee emp) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+emp.toString());
		Employee prevEmp=empRepo.findTopByOrderByIdDesc().get();
		if (prevEmp.getId()==0)
			emp.setId(1);
		else
			emp.setId(prevEmp.getId()+1);
		empRepo.save(emp);
		logger.info(Constants.END_METHOD);
		return true;
	}
	
	public Employee retrieveEmployeeByMail(String email) {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+email);
		Employee employee=empRepo.findByEmail(email).get();
		
		logger.info(Constants.METHOD_RETURN+employee.toString());
		logger.info(Constants.END_METHOD);

		return employee;
	}
}
