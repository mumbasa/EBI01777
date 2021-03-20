package com.shrinq.model;

import java.util.Date;

public class DateResponse {

	private String date;

	public String getDate() {
		return date;
	}

	public DateResponse() {
		setDate();
	}
	public void setDate() {
		this.date = new Date().toString();
	}
	
	
}
