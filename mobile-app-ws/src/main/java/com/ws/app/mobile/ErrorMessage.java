package com.ws.app.mobile;

import java.io.Serializable;
import java.util.Date;

public class ErrorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6833751856529505706L;
	private Date date;
	private String message;

	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage(Date date, String message) {
		super();
		this.date = date;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
