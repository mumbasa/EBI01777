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
		
		// making sure destination has no 2 rocks surronding it
		/*
		 * else
		 * if(treasures[targetXPosition-1][targetYPosition].getType().contains("rock")&!
		 * treasures[targetXPosition-2][targetYPosition].getType().contains("bomb") &
		 * treasures[targetXPosition-1][targetYPosition-1].getType().contains("rock")&!
		 * treasures[targetXPosition-2][targetYPosition-1].getType().contains("bomb") &
		 * treasures[targetXPosition][targetYPosition-1].getType().contains("rock")&!
		 * treasures[targetXPosition][targetYPosition-2].getType().contains("bomb")) {
		 * return false;
		 * 
		 * }
		 */else {

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

		// get summation of row sum
		for (int x = currentX; x < targetXPosition; x++) {
			Treasure t = treasures[currentY][x];
			if (t.getType().equalsIgnoreCase("coin")) {
				totalx += treasures[currentY][x].getAmount();
			} else if (t.getType().equalsIgnoreCase("bomb")) {
				totalx += 0;
			} else if (t.getType().equalsIgnoreCase("rock")) {

				totalx += getRockyNeighbours(currentY, currentX);

			}
		}

		// if end of x axis get the y zxis sum
		if (currentX + 1 <= targetXPosition) {
			for (int y = currentY; y >= targetYPosition; y--) {
				if (treasures[y][currentX + 1].getType().equalsIgnoreCase("coin")) {
					totalx2 += treasures[y][currentX + 1].getAmount();

				} else if (treasures[y][currentX + 1].getType().equalsIgnoreCase("bomb")) {
					totalx2 += 0;
				} else {
					totalx2 += getRockyYNeighbours(currentX, currentY);
				}
			}
		}

		// getting summation of y axis
		for (int x = currentX; x < targetXPosition; x++) {

			if (treasures[currentY - 1][x].getType().equalsIgnoreCase("coin")) {
				totalY += treasures[currentY - 1][x].getAmount();
			} else if (treasures[currentY - 1][x].getType().equalsIgnoreCase("bomb")) {
				totalY += 0;
			} else {
				totalY += getRockyYNeighbours(currentX, currentY);

			}

		}

		// getting summation of the x axis values of current value
		if (currentY - 1 >= targetYPosition) {

			for (int y = currentY; y > targetYPosition; y--) {

				if (treasures[y - 1][currentX].getType().equalsIgnoreCase("coin")
						| treasures[y - 1][currentX].getType().equalsIgnoreCase("bomb")) {
					totalY2 += treasures[y - 1][currentX].getAmount();
				} else {
					totalY2 += getRockyNeighbours(currentX, currentY);
				}

			}
		}

		char data = Math.max(totalx2, totalx) > Math.max(totalY2, totalY) ? 'x' : 'y';

		return data;
	}

	public int getRockyNeighbours(int xIndex, int yIndex) {
		int rocks = 0;
		int bomb = 0;

		for (int x = xIndex + 1; x < targetXPosition; x++) {
			if (treasures[yIndex][x].getType().equals("rock")) {
				rocks++;
			} else if (treasures[yIndex][x].getType().equals("bomb")) {
				bomb++;
			}

		}
		if (rocks > bomb) {
			return -100;
		} else {
			return 0;
		}

	}

	public int getRockyYNeighbours(int xIndex, int yIndex) {
		int rocks = 0;
		int bomb = 0;

		for (int y = yIndex ; y > targetYPosition; y--) {
			if (treasures[y][xIndex].getType().equals("rock")) {
				rocks++;
			} else if (treasures[y][xIndex].getType().equals("bomb")) {
				bomb++;
			}

		}
		if (rocks > bomb) {
			return -100;
		}
		
		else {
			return 0;
		}

	}

	public Result getResult() {
		result = new Result();
		long maxCoins =0;
		List<Character> movements = new ArrayList<Character>();
		if (this.getMapValidity()) {

			//assigning the the starting position
			int currentX = this.getStartXPosition();
			int currentY = this.getStartYPosition();
			
			//checking for the maximum values till destination is reached
			while (currentY >= this.getTargetYPosition() | currentX <= this.getTargetXPosition()) {
				char move = movement(currentX, currentY);
				movements.add(move);
				System.err.println(move);
				if (move == 'x') {
					if (currentX + 1 <= this.getTargetXPosition()) {

						currentX++;

					}
				} else {
					if (currentY >= this.getTargetYPosition()) {
						--currentY;

					}
				}
				if (currentX == this.getTargetXPosition() & currentY == this.getTargetYPosition()) {
					break;
				}

			}

			int cx = this.getStartXPosition();
			int cy = this.getStartYPosition();

			int vy = (this.getTreasures().length - 1) - cy;
			String start = cx + "," + vy;
			List<Integer> startPoint = new ArrayList<Integer>();
			startPoint.add(cx);
			startPoint.add(vy);
			result.getPath().add(startPoint);
			System.out.println(start);

			for (int i = 0; i < movements.size(); i++) {
				if (movements.get(i) == 'x') {
					cx++;

				} else {
					vy++;
					cy--;
				}
				maxCoins += treasures[cy][cx].getAmount();
				List<Integer> s = new ArrayList<Integer>();
				s.add(cx);
				s.add(vy);
				result.getPath().add(s);

				System.err.println(cx + "," + cy + "\t" + cx + "," + vy);
			}
		}
		/*
		 * System.err.println(treasures[0][1].getAmount() + "max " + (coins -
		 * this.getTreasures()[this.getTargetYPosition()][this.getTargetXPosition()].
		 * getAmount()));
		 */
		result.setCoins(maxCoins - this.getTreasures()[this.getTargetYPosition()][this.getTargetXPosition()].getAmount());
	
		return result;
	}

}
