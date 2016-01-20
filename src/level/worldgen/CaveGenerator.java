package level.worldgen;

import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Dimension;

import level.Level;
import level.Tile;
import level.CollisionType;

public class CaveGenerator {
	
	private static Random r;

	public CaveGenerator(int seed){

		r = new Random(seed);

	}

	public CaveGenerator(){

		r = new Random();

	}

	public Level createLevel(int width, int height, int steps, float percentLand){
		
		CaveNode[][] cave1 = generateMap(width, height, steps, percentLand);
		boolean[][] cave = new boolean[height][width];
		
		for (int i = 0; i < cave1.length; i++){
			
			for (int j = 0; j < cave1[0].length; j++){
				
				cave[i][j] = !cave1[i][j].empty;
				
			}
		}
		
		Level l = new Level(width, height, "Cave");
		l.addLayer("caves.png");
		
		for (int i = 0; i < cave.length; i++){
			
			for (int j = 0; j < cave[0].length; j++){
				
				CollisionType collision = cave[i][j] ? CollisionType.WALL : CollisionType.NONE;
				
				l.addTile(TileMapper.processTile(j, i, cave), j, i, 0);	
				l.addCollision(j, i, collision);
				
			}
		}
		
		return l;
	}
	
	public CaveNode[][] generateMap(int width, int height, int steps, float percentLand){

		boolean[][] world = new boolean[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				world[i][j] = !(r.nextFloat() < percentLand);

			}
		}

		//Run run cells then transfer data to CaveNodes
		world = run(steps, world);
		CaveNode[][] cave = new CaveNode[height][width];

		for (int i = 0; i< height; i++){

			for (int j = 0; j < width; j++){

				cave[i][j] = new CaveNode();
				cave[i][j].empty = world[i][j];

			}
		}

		//Use flood-fill to tag all separate cave systems
		int currentTag = 0;

		for (int i = 0; i< height; i++){

			for (int j = 0; j < width; j++){

				if (cave[i][j].empty && cave[i][j].tag == 0){

					currentTag++;
					flood(j, i, currentTag, 0, 0, cave);

				}
			}
		}


		//Get rid of all caves except largest
		int mostTagged = getLargestArea(cave);

		for (int i = 0; i< height; i++){

			for (int j = 0; j < width; j++){

				if (cave[i][j].tag != mostTagged){

					cave[i][j].tag = 0;
					cave[i][j].empty = false;

				}
			}
		}
		
		return cave;

	}

	//Simulates a cell generation
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

	//tags all connected cave tiles matching target tag recursivley
	private void flood(int x, int y, int newTag, int targetTag, int depth, CaveNode[][] map){

		if ((x < 0) && (y < 0) && (x >= map[0].length) && (y >= map.length)){
			
			return;
			
		} else if (!(map[y][x].tag == targetTag) || !(map[y][x].empty) || (depth > 4800)){
			
			return;
			
		} else {
			
			map[y][x].tag = newTag;
			flood(x + 1, y, newTag, targetTag, depth + 1, map);
			flood(x - 1, y, newTag, targetTag, depth + 1, map);
			flood(x, y - 1, newTag, targetTag, depth + 1, map);
			flood(x, y + 1, newTag, targetTag, depth + 1, map);
			
		}
	}

	//goes through all tags and returns the largest
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

			if (tags.get(tag) > highestTagAmount){

				highestTagAmount = tags.get(tag);
				highestTag = tag;

			}

		}
		
		return highestTag;

	}

	//class to just hold the tags for each array item
	public class CaveNode {

		public int tag;
		public boolean empty;

	}
}