package com.shrinq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shrinq.model.DateResponse;

@RestController
public class DateController {

	@GetMapping("/date")
	public DateResponse getDate() {
		return new DateResponse();
		
	}
}
