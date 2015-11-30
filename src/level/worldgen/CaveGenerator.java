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

		Level l = new Level(width, height, "Forest", "blowhard_forest_dark.png");
		l.addLayer();
		
		for (int i = 0; i < height; i++){
			
			for (int j = 0; j < width; j++){
				
				Tile t = new Tile(0);
				
				if (!cave[i][j].empty){
					
					t = new Tile(r.nextInt(1) == 0 ? 21 : 38);

					boolean[] n = getCardinalNeighbours(j, i, cave);

					if (!n[0] && n[1] && !n[2] && n[3]){

						t = new Tile(54);

					}

					if (n[0] && !n[1] && n[2] && !n[3]){

						t = new Tile(71);

					}

					//   X
					//  OOO
					//
					if (!n[0] && !n[1] && n[2] && !n[3]){

						t = new Tile(17);

					}

					//   O
					//  XO
					//   O
					if (!n[0] && n[1] && !n[2] && !n[3]){

						t = new Tile(51);

					}

					//   O
					//   OX
					//   O
					if (!n[0] && !n[1] && !n[2] && n[3]){

						t = new Tile(34);

					}

					//
					//  OOO
					//   X
					if (n[0] && !n[1] && !n[2] && !n[3]){

						t = new Tile(68);

					}

					//  XOO
					//  O
					//  O
					if (!n[0] && n[1] && n[2] && !n[3]){

						t = new Tile(5);

					}

					//  OOX
					//    O
					//    O
					if (!n[0] && !n[1] && n[2] && n[3]){

						t = new Tile(7);

					}

					//  O
					//  O
					//  XOO
					if (n[0] && n[1] && !n[2] && !n[3]){

						t = new Tile(39);

					}

					//    O
					//    O
					//  OOX
					if (n[0] && !n[1] && !n[2] && n[3]){

						t = new Tile(41);

					}

					//  OXO
					//  
					//
					if (!n[0] && n[1] && n[2] && n[3]){

						t = new Tile(6);

					}

					//
					//
					//  OXO
					if (n[0] && n[1] && !n[2] && n[3]){

						t = new Tile(40);

					}

					//  O
					//  X
					//  O
					if (n[0] && n[1] && n[2] && !n[3]){

						t = new Tile(22);

					}

					//    O
					//    X
					//    O
					if (n[0] && !n[1] && n[2] && n[3]){

						t = new Tile(24);

					}

					if (n[0] && n[1] && n[2] && n[3]){

						t = new Tile(23);

					}

				} else {
					
					switch (r.nextInt(5)){

						case 0:
							t = new Tile(0);
							break;
						case 1:
							t = new Tile(1);
							break;
						case 2:
							t = new Tile(2);
							break;
						case 3:
							t = new Tile(18);
							break;
						case 4:
							t = new Tile(19);
							break;

					}
					
				}
				
				l.addTile(t, j, i);
			}
		}
		
		return l;
	}
	
	public CaveNode[][] generateMap(int width, int height, int steps, float percentLand){

		double timer = System.currentTimeMillis();

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

		//System.out.println("Generated [" + width + "x" + height + "] map in " + (System.currentTimeMillis() - timer) + " ms");
		return cave;

	}
	
	private boolean[] getCardinalNeighbours(int x, int y, CaveNode[][] cave){
		
		boolean[] neighbours = new boolean[8];
		
		neighbours[0] = (y - 1 >= 0) ? !cave[y - 1][x].empty : true;
		neighbours[1] = (x + 1 <= cave[0].length - 1) ? !cave[y][x + 1].empty : true;
		neighbours[2] = (y + 1 <= cave.length - 1) ? !cave[y + 1][x].empty : true;
		neighbours[3] = (x - 1 >= 0) ? !cave[y][x - 1].empty : true;
		neighbours[4] = (y - 1 >= 0 && x + 1 <= cave[0].length - 1) ? !cave[y - 1][x + 1].empty : true;
		neighbours[5] = (y + 1 <= cave.length - 1 && x + 1 <= cave[0].length - 1) ? !cave[y + 1][x + 1].empty : true;
		neighbours[6] = (y + 1 <= cave.length - 1 && x - 1 >= 0) ? !cave[y + 1][x - 1].empty : true;
		neighbours[7] = (y - 1 >= 0 && x - 1 >= 0) ? !cave[y - 1][x - 1].empty : true;
		
		return neighbours;
	}

	private boolean[][] run(int steps, boolean[][] initialGrid){

		for (int i = 0; i < steps; i++){

			//System.out.println("step");
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
			
			//System.out.println("Tag: " + tag + " | " + tags.get(tag));

			if (tags.get(tag) > highestTagAmount){

				highestTagAmount = tags.get(tag);
				highestTag = tag;

			}

		}

		//System.out.println("Highest Tag: " + highestTag + " (" + tags.get(highestTag) + ")");
		return highestTag;

	}

	public class CaveNode {

		public int tag;
		public boolean empty;

	}
}