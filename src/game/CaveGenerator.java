package game;

import java.util.Random;
import java.awt.Dimension;

public class CaveGenerator {
	
	private Random r;
	private Dimension gridSize;

	public CaveGenerator(int width, int height){

		gridSize = new Dimension(width, height);

	}

	public boolean[][] generateMap(int seed, int steps, float percentLand){

		boolean[][] world = new boolean[gridSize.height][gridSize.width];
		r = new Random(seed);

		for (int i = 0; i < gridSize.height; i++){

			for (int j = 0; j < gridSize.width; j++){

				world[i][j] = (r.nextFloat() < percentLand);

			}
		}

		return run(steps, world);

	}

	private boolean[][] run(int steps, boolean[][] initialGrid){

		for (int i = 0; i < steps; i++){

			System.out.println("step");
			boolean[][] nextGen = new boolean[initialGrid.length][initialGrid[0].length];

			for (int j = 0; j < initialGrid.length; j++){

				for (int k = 0; k < initialGrid[0].length; k++){

					nextGen[j][k] = nextGeneration(k, j, initialGrid);

				}
			}

			initialGrid = nextGen;

		}

		return initialGrid;

	}

	private boolean nextGeneration(int x, int y, boolean[][] grid){

		int neighbours = 0;

		for (int i = (y - 1); i < (y + 1); i++){

			for (int j = (x - 1); j < (x + 1); j++){

				try {

					neighbours += (grid[i][j] && !((i == y) && (j == x))) ? 1 : 0;

				} catch (Exception e){}
			}
		}

		if (neighbours == 3 && grid[y][x]){

			return true;

		} else if (neighbours >= 4){

			return true;

		} else {

			return false;

		}
	}
}