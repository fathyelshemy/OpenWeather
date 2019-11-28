package com.weather.forcast.model;

import io.swagger.annotations.ApiModelProperty;

public class Email {
	
	@ApiModelProperty(required = true,allowEmptyValue = false,example = "example@mail.com")
	private String mail;

	public String getEmail() {
		return mail;
	}

	public void setEmail(String email) {
		this.mail = email;
	}
	

}
