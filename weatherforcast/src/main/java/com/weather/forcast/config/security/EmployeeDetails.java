package com.weather.forcast.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.weather.forcast.model.Employee;

public class EmployeeDetails implements UserDetails {
	
	private Employee emp;
	
	private ArrayList<SimpleGrantedAuthority> authorities;
	
	public EmployeeDetails(Employee emp) {
		this.emp=emp;
		ArrayList<SimpleGrantedAuthority> authorities= new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(emp.getRole()));
		this.authorities=authorities;
	}
		
	public EmployeeDetails() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return emp.getPassword();
	}

	@Override
	public String getUsername() {

		return emp.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
