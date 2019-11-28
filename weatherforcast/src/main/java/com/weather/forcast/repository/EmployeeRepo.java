package com.weather.forcast.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weather.forcast.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByEmail(String email);
	
	public Optional<Employee> findTopByOrderByIdDesc();
}
