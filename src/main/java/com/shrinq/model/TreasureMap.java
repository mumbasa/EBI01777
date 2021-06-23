package com.shrinq.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreasureMap {

	private Treasure[][] treasures;
	private int startXPosition;
	private int startYPosition;
	private int targetXPosition;
	private int targetYPosition;
	private Result result;

	public boolean getMapValidity() {
		if (this.startXPosition <= this.targetXPosition & this.startYPosition >= this.targetYPosition) {
			return true;

		}

		else {

			return false;
		}

	}

	public char movement2(int currentX, int currentY) {
		long totalY = 0;
		long totalY2 = 0;
		long totalx = 0;
		long totalx2 = 0;

		// get summation of row sum
		for (int x = currentX; x < targetXPosition; x++) {
			totalx += treasures[currentY][x].getAmount();

		}

		// if end of x axis get the y zxis sum
		if (currentX + 1 <= targetXPosition) {
			for (int y = currentY; y >= targetYPosition; y--) {
				totalx2 += treasures[y][currentX + 1].getAmount();

			}
		}

		// getting summation of y axis
		for (int x = currentX; x < targetXPosition; x++) {
			totalY += treasures[currentY - 1][x].getAmount();

		}

		// getting summation of the x axis values of current value
		if (currentY - 1 >= targetYPosition) {

			for (int y = currentY; y > targetYPosition; y--) {
				totalY2 += treasures[y - 1][currentX].getAmount();

			}
		}

		char data = Math.max(totalx2, totalx) > Math.max(totalY2, totalY) ? 'x' : 'y';

		return data;
	}

	// getting summation of next index columns and rows the one with the most value
	// would be selected as next
	public char movement(int currentX, int currentY) {
		long totalY = 0;
		long totalY2 = 0;
		long totalx = 0;
		long totalx2 = 0;
		if (treasures[currentY][currentX].getType().contains("bomb")) {
			System.err.println("blasting");
			blastTransform(currentX, currentY);
			return bombNextMaximumNeighbour(currentX, currentY);

		} else {
			// get summation of row sum
			for (int x = currentX; x < targetXPosition; x++) {
				Treasure t = treasures[currentY][x];
				if (t.getType().equalsIgnoreCase("coin")) {
					totalx += treasures[currentY][x].getAmount();
				} else if (t.getType().equalsIgnoreCase("bomb")) {

				} else if (t.getType().equalsIgnoreCase("rock")) {
					break;
				}
			}

			// if end of x axis get the y zxis sum
			if (currentX + 1 <= targetXPosition) {
				for (int y = currentY; y >= targetYPosition; y--) {
					if (treasures[y][currentX + 1].getType().equalsIgnoreCase("coin")) {
						totalx2 += treasures[y][currentX + 1].getAmount();

					} else if (treasures[y][currentX + 1].getType().equalsIgnoreCase("bomb")) {
					} else {

						break;
					}
				}
			}

			// getting summation of y axis
			for (int x = currentX; x < targetXPosition; x++) {
				Treasure treasureT = treasures[currentY][x];
				if (treasureT.getType().equalsIgnoreCase("coin")) {
					totalY += treasureT.getAmount();
				} else if (treasureT.getType().equalsIgnoreCase("bomb")) {
				} else {

					break;
				}

			}

			// getting summation of the x axis values of current value
			if (currentY - 1 >= targetYPosition) {

				for (int y = currentY - 1; y > targetYPosition; y--) {

					if (treasures[y - 1][currentX].getType().equalsIgnoreCase("coin")) {
						totalY2 += treasures[y][currentX].getAmount();

					} else if (treasures[y][currentX].getType().equalsIgnoreCase("bomb")) {
					} else {
						break;
					}

				}

			}
			char data = Math.max(totalx2, totalx) > Math.max(totalY2, totalY) ? 'x' : 'y';
			return data;
		}
	}

	public void blastTransform(int xIndex, int yIndex) {

		for (int x = xIndex; x <= xIndex + 1; x++) {
			for (int y = yIndex; y >= yIndex - 1; y--) {
				try {
					Treasure treasureNow = treasures[y][x];
					if (treasureNow.getType().equals("rock")) {
						System.err.println("changing rock to coin");

						treasureNow.setAmount(0);
						treasureNow.setType("coin");
						treasures[y][x] = treasureNow;

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
					
					return;
				}

			}

		}

	}

	public char bombNextMaximumNeighbour(int xIndex, int yIndex) {
		int xAxisSum = 0;
		int yAxisSum = 0;

		for (int x = xIndex; x < targetXPosition; x++) {

			Treasure t = treasures[yIndex][x];
			if (t.getType().equalsIgnoreCase("coin")) {
				xAxisSum += t.getAmount();
			} else if (t.getType().equalsIgnoreCase("bomb")) {

			} else if (t.getType().equalsIgnoreCase("rock")) {

				break;
			}

		}

		for (int y = yIndex; y < targetYPosition; y++) {
			Treasure t = treasures[y][xIndex];
			if (t.getType().equalsIgnoreCase("coin")) {
				yAxisSum += t.getAmount();
			} else if (t.getType().equalsIgnoreCase("bomb")) {

			} else if (t.getType().equalsIgnoreCase("rock")) {

				break;
			}
		}
		return xAxisSum > yAxisSum ? 'x' : 'y';

	}

	public Result getResult() {
		result = new Result();
		int currentXPosition = this.getStartXPosition();
		int currentYPosition = this.getStartYPosition();

		int translatedValueForY = (this.getTreasures().length - 1) - currentYPosition;
//		String start = currentXPosition + "," + translatedValueForY;
		List<Integer> startPoint = new ArrayList<Integer>();
		startPoint.add(currentXPosition);
		startPoint.add(translatedValueForY);
		result.getPath().add(startPoint);
		
		long maxCoins = 0;
		List<Character> movements = new ArrayList<Character>();
		if (this.getMapValidity()) {

			// assigning the the starting position
			int currentX = this.getStartXPosition();
			int currentY = this.getStartYPosition();

			// checking for the maximum values till destination is reached
			while (currentY >= this.getTargetYPosition() | currentX <= this.getTargetXPosition()) {
				char move = movement(currentX, currentY);
				movements.add(move);
//				System.err.println(move);
				List<Integer> coordindates = new ArrayList<Integer>();

				if (move == 'x') {
					if (currentX + 1 <= this.getTargetXPosition()) {

						currentX++;
						maxCoins += treasures[currentY][currentX].getAmount();
						coordindates.add(currentX);
						coordindates.add(translatedValueForY);
						result.getPath().add(coordindates);

					}
				} else {
					if (currentY >= this.getTargetYPosition()) {
						currentY--;
						translatedValueForY++;
						maxCoins += treasures[currentY][currentX].getAmount();
						coordindates.add(currentX);
						coordindates.add(translatedValueForY);
						result.getPath().add(coordindates);

					}
				}
				if (currentX == this.getTargetXPosition() & currentY == this.getTargetYPosition()) {
					break;
				}

			}
		}
			

			result.setCoins(
				maxCoins - this.getTreasures()[this.getTargetYPosition()][this.getTargetXPosition()].getAmount());

		return result;
	}
	
}
