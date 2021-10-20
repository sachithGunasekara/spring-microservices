package com.photo.api.user.ui.model.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateUserDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 81948726683009186L;
	@NotNull(message ="First name cannot be NULL")
	private String firstName;
	
	@NotNull(message ="Last name cannot be NULL")
	private String lastName;
	
	@NotNull(message ="Email cannot be NULL")
	@Email
	private String email;
	
	@NotNull(message ="Password cannot be NULL")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
