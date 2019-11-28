package com.weather.forcast.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private long id;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true,allowEmptyValue = false)
	private String username;
	
	@Email
	@Column(nullable = false)
	@ApiModelProperty(required = true,allowEmptyValue = false,example = "example@mail.com")
	private String email;
	
	@Column(nullable = false)
	@Min(value = 8)
	@ApiModelProperty(required = true,allowEmptyValue = false,notes = "must be 8 digits or more ")
	private String password;
	@ApiModelProperty(required = false,allowEmptyValue = true)
	private String mobileNumber;
	
	@ApiModelProperty(hidden = true)
	@Column(nullable = false,columnDefinition = "varchar(20) default 'ROLE_USER'")
	private String role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", role=" + role + "]";
	}

	
	
}
