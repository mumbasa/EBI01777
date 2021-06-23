package com.shrinq.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.shrinq.model.Treasure;
import com.shrinq.model.TreasureMap;

@Repository
public class CoinRepository {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */

	public Object solveProblemFormMapInFile(int startXPosition, int startYPosition, int targetXPosition, int targetYPosition) {

		Treasure[][] treasures = (Treasure[][]) ReadObjectFromFile("/home/bryan/map.dat");
		for (int x = 0; x < treasures.length; x++) {

			for (int y = 0; y < treasures[x].length; y++) {
		
				System.err.print(treasures[x][y].getAmount()+ " ");

			}
			System.out.println();
		}
		
		TreasureMap map = new TreasureMap();
		map.setTreasures(treasures);
		map.setStartXPosition(startXPosition);
		//translating to Y axis proper
		map.setStartYPosition((treasures.length - 1) - startYPosition);
		map.setTargetXPosition(targetXPosition);

		//translating to Y axis proper
		map.setTargetYPosition((treasures.length - 1) - targetYPosition);
		if (map.getMapValidity()) {
			try {
			return	map.getResult();
			
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Not Found";
			}
		} else {
			return "Not Found";

		}

	}
	
	public Object solveProblem(int startXPosition, int startYPosition, int targetXPosition, int targetYPosition) {

		Treasure[][] treasures = new Treasure[4][4];
		int k = 0;
	
		for (int x = 0; x < treasures.length; x++) {

			for (int y = 0; y < treasures[x].length; y++) {
				k++;

				Treasure t = new Treasure("coin", k);
				treasures[x][y] = t;

				System.err.print(k + " ");

			}
			System.err.println();

		}

		TreasureMap map = new TreasureMap();
		map.setTreasures(treasures);
		map.setStartXPosition(startXPosition);
		map.setStartYPosition((treasures.length - 1) - startYPosition);
		map.setTargetXPosition(targetXPosition);
		map.setTargetYPosition((treasures.length - 1) - targetYPosition);
		if (map.getMapValidity()) {
			try {
				map.getResult();
				return map;
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Not Found";
			}
		} else {
			return "Not Found";

		}

	}

	public static String WriteObjectToFile(Object serObj) {

		try {

			FileOutputStream fileOut = new FileOutputStream("/home/bryan/map.dat");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(serObj);
			objectOut.close();
			return "The Object  was succesfully written to a file";

		} catch (Exception ex) {
			return "error";

		}
	}

	
	public static Object ReadObjectFromFile(String filepath) {
		 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj =  objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	public static void main(String[] args) {
		Treasure[][] mapper = (Treasure[][]) ReadObjectFromFile("/home/bryan/map2.dat");
		
		
		

		TreasureMap map = new TreasureMap();
		map.setTreasures(mapper);
		map.setStartXPosition(0);
		map.setStartYPosition(3);
		map.setTargetXPosition(3);
		map.setTargetYPosition(0);
		System.err.println(	map.getResult().getCoins());

	
	}

}
