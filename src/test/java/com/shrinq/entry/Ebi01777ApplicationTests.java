package com.shrinq.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.shrinq.model.Treasure;
import com.shrinq.model.TreasureMap;
import com.shrinq.repository.CoinRepository;

@SpringBootTest
class Ebi01777ApplicationTests {

	@Test
	void contextLoads() {
		
		
	}

	@Test
	public void testData() {
		Treasure[][] mapper = (Treasure[][]) CoinRepository.ReadObjectFromFile("/home/bryan/map2.dat");
		TreasureMap map = new TreasureMap();
		map.setTreasures(mapper);
		map.setStartXPosition(0);
		map.setStartYPosition(3);
		map.setTargetXPosition(3);
		map.setTargetYPosition(0);
		assertEquals(57, map.getResult().getCoins(),"Coins should be 57");
	}
}
