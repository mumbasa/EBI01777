package com.shrinq.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Treasure implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	private int amount;
	
	public Treasure() {
		// TODO Auto-generated constructor stub
	}

	public Treasure(String type,int amount) {
		this.amount=amount;
		this.type=type;
		
	}
}
