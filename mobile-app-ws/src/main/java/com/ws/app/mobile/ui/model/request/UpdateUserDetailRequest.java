package com.ws.app.mobile.ui.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UpdateUserDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -919134619020199835L;

	@NotNull(message = "First name cannot be NULL")
	private String firstName;
	
	@NotNull(message = "Last name cannot be NULL")
	private String lastName;

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

}
