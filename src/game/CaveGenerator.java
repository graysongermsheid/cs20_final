package game;

import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Dimension;

public class CaveGenerator {
	
	private static Random r;

	public CaveGenerator(int seed){

		r = new Random(seed);

	}

	public CaveGenerator(){

		r = new Random();

	}

	public Level createLevel(int width, int height, int steps, float percentLand){
		
		CaveNode[][] cave = generateMap(width, height, steps, percentLand);
		Level l = new Level();
		
		l.tileSet = "tileset.png";
		l.name = "cave";
		l.width = width;
		l.height = height;
		l.addLayer();
		
		for (int i = 0; i < height; i++){
			
			for (int j = 0; j < width; j++){
				
				Tile t = new Tile(0);
				
				if (!cave[i][j].empty){
					
					boolean[] b = getCardinalNeighbours(j, i, cave);
					
					if ((b[0] & b[1] & b[2] & b[3])){
						
						t = new Tile(50);
						
					} else if (!b[0] && b[1] && b[2] && !b[3]){ //|-
						
						t = new Tile(23);
						
					} else if (!b[0] && !b[1] && b[2] && b[3]){ //-|
						
						t = new Tile(24);
						
					} else if (b[0] && !b[1] && !b[2] && b[3]){ // _|
						
						t = new Tile(34);
						
					} else if (b[0] && b[1] && !b[2] && !b[3]){ // |_
						
						t = new Tile(33);
						
					} else if (!b[0] && b[1] && b[3]){ //_
						
						t = new Tile(50);
						
					} else if (!b[2] && b[1] && b[3]){//--
						
						t = new Tile(21);
						
					} else if (!b[3] && b[0] && b[2]){ //|x
						
						t = new Tile(32);
						
					} else if (!b[1] && b[0] && b[2]){
						
						t = new Tile(30);
						
					}
					
				} else {
					
					t = new Tile(19);
					
				}
				
				l.addTile(t, j, i);
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
					flood(j, i, currentTag, cave);

				}
			}
		}


		//Get rid of all caves except largest
		int mostTagged = getLargestArea(cave);
		boolean[][] taggedWorld = world.clone();

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
	
	private boolean[] getCardinalNeighbours(int x, int y, CaveNode[][] cave){
		
		int size = 4;
		
		boolean[] neighbours = new boolean[4];
		
		neighbours[0] = (y - 1 >= 0) ? !cave[y - 1][x].empty : true;
		neighbours[1] = (x + 1 <= cave[0].length - 1) ? !cave[y][x + 1].empty : true;
		neighbours[2] = (y + 1 <= cave.length - 1) ? !cave[y + 1][x].empty : true;
		neighbours[3] = (x - 1 >= 0) ? !cave[y][x - 1].empty : true;
		
		return neighbours;
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