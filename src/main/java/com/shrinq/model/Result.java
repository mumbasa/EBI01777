package com.shrinq.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private List<List<Integer>> path;
	private long coins;

	public Result() {
		// TODO Auto-generated constructor stub
	path = new ArrayList<List<Integer>>();
	}
}
