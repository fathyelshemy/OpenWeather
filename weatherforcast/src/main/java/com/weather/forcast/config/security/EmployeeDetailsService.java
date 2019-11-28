package com.weather.forcast.config.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weather.forcast.model.Employee;
import com.weather.forcast.repository.EmployeeRepo;
import com.weather.forcast.utilies.Constants;

@Service
public class EmployeeDetailsService implements UserDetailsService {
	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepo empRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(Constants.CLASS_NAME+this.getClass().getName()+Constants.METHOD_NAME+new Object() {}.getClass().getEnclosingMethod().getName());
		logger.debug(Constants.METHOD_ARGUMENTS+username);
		Optional<Employee>optionalEmp=empRepo.findByEmail(username);
		optionalEmp.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
//		UserDetails as = optionalEmp.map(EmployeeDetails::new).get();
		logger.info(Constants.END_METHOD);
		return optionalEmp.map(EmployeeDetails::new).get();
	}

}
