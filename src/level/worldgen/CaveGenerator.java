package level.worldgen;

import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Dimension;

import level.Level;
import level.Tile;

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
		Level l = new Level(width, height, "Cave", "Tolos.png");
		l.addLayer();
		
		for (int i = 0; i < cave.length; i++){
			
			for (int j = 0; j < cave[0].length; j++){
				
				l.addTile(interpretTile(j, i, cave), j, i, 0);
			}
		}
		
		return l;
	}

	private Tile interpretTile(int x, int y, CaveNode[][] map){

		Tile t = new Tile(7);
		
		if (map[y][x].empty){
			
			return t;

		}
		
		t = new Tile(35);

		boolean nw = (x > 0 && y > 0) ? !map[y - 1][x - 1].empty : true;                      //North-West neighbour
		boolean n  = (y > 0) ? !map[y - 1][  x  ].empty : true;                               //North neighbour
		boolean ne = (x < map[0].length - 1 && y > 0) ? !map[y - 1][x + 1].empty : true;          //North-East neighbour
		boolean w  = (x > 0) ? !map[  y  ][x - 1].empty : true;                               //West neighbour
		boolean e  = (x < map[0].length - 1) ? !map[  y  ][x + 1].empty : true;                   //East neighbour
		boolean se = (x < map[0].length - 1 && y < map.length - 1) ? !map[y + 1][x + 1].empty : true;             //South-East neighbour
		boolean s  = (y < map.length - 1) ? !map[y + 1][  x  ].empty : true;                      //South neighbour
		boolean sw = (x > 0 && y < map.length - 1) ? !map[y + 1][x - 1].empty : true; //South-West neighbour

		//Tile tile = new Tile(90);

		if (n & w & s & !se & e){
			
			t = new Tile(0);
			
		} else if (n & e & w & !s){
			
			if (!ne & !nw){
				
				t = new Tile(41);
				
			} else if (!ne){
				
				t = new Tile(29);
				
			} else if (!nw){
				
				t = new Tile(39);
				
			} else {
				
				t = new Tile(1);
				
			}
			
		} else if (n & w & s & !sw & e){
			
			t = new Tile(2);
			
		} else if (n & w & !e & s){
			
			if (!nw & !sw){
				
				t = new Tile(34);
				
			} else if (!nw){
				
				t = new Tile(37);
				
			} else if (!sw){
				
				t = new Tile(43);
				
			} else {
				
				t = new Tile(6);
				
			}
			
		} else if (n & !w & e & s){
			
			if (!ne & !se){
				
				t = new Tile(33);
				
			} else if (!ne){
				
				t = new Tile(36);
				
			} else if (!se){
				
				t = new Tile(42);
				
			} else {
				
				t = new Tile(8);
				
			}
			
		} else if (n & w & e & s & !ne){
			
			t = new Tile(12);
		
		} else if (!n & w & e & s){
			
			if (!sw & !se){
				
				t = new Tile(40);
				
			} else if (!sw){
				
				t = new Tile(23);
				
			} else if (!se){
				
				t = new Tile(38);
				
			} else {
				
				t = new Tile(13);
				
			}
			
		} else if (n & w & e & s & !nw){
			
			t = new Tile(14);
			
		} else if (!n & !w & e & s){
			
			if (!se){
				
				t = new Tile(44);
				
			} else {
				
				t = new Tile(3);
				
			}
			
		} else if (!n & w & !e & s){
			
			if (!sw){
				
				t = new Tile(45);
				
			} else {
				
				t = new Tile(4);
				
			}
			
		} else if (n & !w & e & !s){
			
			if (!ne){
				
				t = new Tile(46);
				
			} else {
				
				t = new Tile(9);
				
			}
			
		} else if (n & w & !e & !s){
			
			if (!nw){
				
				t = new Tile(47);
				
			} else {
				
				t = new Tile(10);
				
			}
			
		} else if (!n & !w & !e & s){
			
			t = new Tile(19);
			
		} else if (!n & !w & e & !s){
			
			t = new Tile(24);
			
		} else if (n & !w & !e & !s){
			
			t = new Tile(31);
			
		} else if (!n & w & !e & !s){
			
			t = new Tile(26);
			
		} else if (n & !w & !e & s){
			
			t = new Tile(17);
			
		} else if (!n & e & w & !s){
			
			t = new Tile(11);
			
		}

		if (n & w & s & e){

			if (!nw & !ne & !sw & !se){
			
				t = new Tile(25);
			
			} else if (!nw & !se & !sw){

				t = new Tile(21);

			} else if (!sw & !ne & !se){

				t = new Tile(22);

			} else if (!sw & !ne & !nw){

				t = new Tile(15);

			} else if (!nw & !se & !ne){

				t = new Tile(16);

			} else if (!nw & !ne){
			
				t = new Tile(20);
			
			} else if (!nw & !sw){
				
				t = new Tile(18);
			
			} else if (!sw & !se){
			
				t = new Tile(30);
			
			} else if (!ne & !se){
			
				t = new Tile(32);
			
			} else if (!ne & !sw){

				t = new Tile(27);

			} else if (!nw & !se){

				t = new Tile(28);

			}

		}
		
		return t;

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