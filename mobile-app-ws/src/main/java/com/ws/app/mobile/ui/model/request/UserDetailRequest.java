package com.ws.app.mobile.ui.model.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322388956387507426L;

	@NotNull(message = "First name cannot be empty")
	private String firstName;

	@NotNull(message = "Last name cannot be empty")
	private String lastName;

	@NotNull(message = "Email cannot be empty")
	@Email
	private String email;

	@NotNull(message = "Password cannot be empty")
	@Size(min = 8, max = 16, message = "Password should be greate than or equal to 8 or less than or equal to 16 characters")
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
