package com.ws.app.mobile.ui.model.response;

import java.io.Serializable;

public class UserRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5546968398286505877L;
	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
