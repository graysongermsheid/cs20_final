package game;

import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Dimension;

public class CaveGenerator {
	
	private Random r;
	private Dimension gridSize;

	public CaveGenerator(int width, int height){

		gridSize = new Dimension(width, height);

	}

	public CaveNode[][] generateMap(int steps, float percentLand){

		boolean[][] world = new boolean[gridSize.height][gridSize.width];
		Random r2 = new Random();
		r = new Random(r2.nextInt(0x4000001D));

		for (int i = 0; i < gridSize.height; i++){

			for (int j = 0; j < gridSize.width; j++){

				world[i][j] = !(r.nextFloat() < percentLand);

			}
		}

		world = run(steps, world);
		CaveNode[][] cave = new CaveNode[gridSize.height][gridSize.width];

		for (int i = 0; i< gridSize.height; i++){

			for (int j = 0; j < gridSize.width; j++){

				cave[i][j] = new CaveNode();
				cave[i][j].empty = world[i][j];

			}
		}

		int currentTag = 0;

		for (int i = 0; i< gridSize.height; i++){

			for (int j = 0; j < gridSize.width; j++){

				if (cave[i][j].empty && cave[i][j].tag == 0){

					currentTag++;
					flood(j, i, currentTag, cave);

				}
			}
		}

		int mostTagged = getLargestArea(cave);
		boolean[][] taggedWorld = world.clone();

		for (int i = 0; i< gridSize.height; i++){

			for (int j = 0; j < gridSize.width; j++){

				if (cave[i][j].tag != mostTagged){

					//cave[i][j].tag = 0;
					//cave[i][j].empty = false;

				}
			}
		}

		return cave;

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

		for (int i = 0; i < initialGrid.length; i++){

			for (int j = 0; j < initialGrid[0].length; j++){

				if (i == 0 ||
					j == 0 ||
					i == initialGrid.length - 1 ||
					j == initialGrid[0].length - 1){

					initialGrid[i][j] = false;

				}
			}
		}

		return initialGrid;

	}

	private boolean nextGeneration(int x, int y, boolean[][] grid){

		int neighbours = 0;

		for (int i = (y - 1); i <= (y + 1); i++){

			for (int j = (x - 1); j <= (x + 1); j++){

				try {

					neighbours += (grid[i][j] && !((i == y) && (j == x))) ? 1 : 0;

				} catch (Exception e){}
			}
		}

		if (neighbours == 4 && grid[y][x]){

			return true;

		} else if (neighbours > 4){

			return true;

		} else {

			return false;

		}
	}

	private void flood(int x, int y, int tag, CaveNode[][] map){

		map[y][x].tag = tag;

		if ((x > 0) && (map[y][x - 1].tag != tag) && (map[y][x - 1].empty)){

			flood(x - 1, y, tag, map);

		}

		if ((y > 0) && (map[y - 1][x].tag != tag) && (map[y - 1][x].empty)){

			flood(x, y - 1, tag, map);

		}

		if ((x < map[0].length - 1) && (map[y][x + 1].tag != tag) && (map[y][x + 1].empty)){

			flood(x + 1, y, tag, map);

		}

		if ((y < map.length - 1) && (map[y + 1][x].tag != tag) && (map[y + 1][x].empty)){

			flood(x, y + 1, tag, map);

		}
	}

	private int getLargestArea(CaveNode[][] map){

		HashMap<Integer, Integer> tags = new HashMap<Integer, Integer>();

		for (int i = 0; i < map.length; i++){

			for (int j = 0; j < map[0].length; j++){

				if (map[i][j].tag != 0){

					if (tags.get(map[i][j].tag) == null){

						tags.put(map[i][j].tag, 1);

					} else {

						tags.put(map[i][j].tag, tags.get(map[i][j].tag) + 1);

					}
				}
			}
		}

		Iterator i = tags.keySet().iterator();
		int highestTag = 0;
		int highestTagAmount = 0;

		while (i.hasNext()){

			int tag = (int) i.next();
			
			System.out.println("Tag: " + tag + " | " + tags.get(tag));

			if (tags.get(tag) > highestTagAmount){

				highestTagAmount = tags.get(tag);
				highestTag = tag;

			}

		}

		System.out.println("Highest Tag: " + highestTag + " (" + tags.get(highestTag) + ")");
		return highestTag;

	}

	public class CaveNode {

		public int tag;
		public boolean empty;

	}
}