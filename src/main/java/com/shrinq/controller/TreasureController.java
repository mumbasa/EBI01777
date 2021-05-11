package com.shrinq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shrinq.model.Treasure;
import com.shrinq.repository.CoinRepository;

@RestController
public class TreasureController {
	
	@Autowired
	CoinRepository coinRepository;

	@GetMapping("/findPath")
	public Object solve(@RequestParam int startXPosition,@RequestParam int startYPosition,@RequestParam int targetXPosition,@RequestParam int targetYPosition) {
		return coinRepository.solveProblemFormMapInFile(startXPosition, startYPosition, targetXPosition, targetYPosition);
		
		
	}
	
	@PostMapping("/map")
	public String postMap(@RequestBody Treasure[][] treasures) {
		return coinRepository.WriteObjectToFile(treasures);
		
	}
}
