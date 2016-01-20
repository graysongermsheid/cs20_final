package level.worldgen;

import level.CollisionType;
import level.Level;
import level.Tile;
import game.Direction;
import resources.ResourceManager;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DungeonGenerator {

	public Level createLevel(int width, int height){
		
		RoomStyle[][] rooms = createMaze(width, height);
		Level l = new Level(width * 9, height * 8, "DUNGEON");
		l.addLayer("dungeon.png");
		
		boolean[][] levelMap = new boolean[height * 8][width * 9];
		
		for (int i = 0; i < height; i++){
			
			for (int j = 0; j < width; j++){
				
				boolean[][] q = readTiles(rooms[i][j].toString());
				
				for (int a = 0; a < 8; a++){
					
					for (int b = 0; b < 9; b++){
						
						levelMap[i * 8 + a][j * 9 + b] = q[a][b];
						
					}
				}
			}
		}
		
		LevelPiece[][] w = mapToLevel(levelMap);
		
		for (int i = 0; i < w.length; i++){
			
			for (int j = 0; j < w[0].length; j++){
				
				l.addTile(w[i][j].t, j, i, 0);
				l.addCollision(j, i, w[i][j].c);
				
			}
		}
		
		return l;
	}
	
	private boolean[][] readTiles(String filename){
		
		BufferedImage plan = ResourceManager.getImage("/levels/" + filename + ".png");
		boolean[][] map = new boolean[8][9];
		
		for (int i = 0; i < 8; i++){
			
			for (int j = 0; j < 9; j++){
				
				map[i][j] = plan.getRGB(j, i) == java.awt.Color.BLACK.getRGB(); //if the plan is black, then it's a wall
				System.out.print(" RGB: " + plan.getRGB(j, i));
				
			}
		}
		
		return map;
	}
	
	private LevelPiece[][] mapToLevel(boolean[][] map){
		
		LevelPiece[][] level = new LevelPiece[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++){
			
			for (int j = 0; j < map[0].length; j++){
				
				Tile t = TileMapper.processTile(j, i, map);
				CollisionType c = CollisionType.fromValue(map[i][j] ? 1 : 0);
				level[i][j] = new LevelPiece(t, c);
				
			}
		}
		
		return level;
		
	}
	
	public RoomStyle[][] createMaze(int width, int height){
		
		RoomStyle[][] rooms = new RoomStyle[height][width];
		
		for (int i = 0; i < height; i++){
			
			for (int j = 0; j < width; j++){
				
				rooms[i][j] = RoomStyle.NONE;
				
			}
		}
		
		Random r = new Random();
		rooms = addRoom(r.nextInt(width - 1) + 1, r.nextInt(height - 1) + 1, rooms);
		
		for (int i = 0; i < height; i++){

			System.out.println();
			for (int j = 0; j < width; j++){
				
				System.out.print(rooms[i][j].toString());
				
			}
		}
		
		return rooms;
		
	}
	
	private RoomStyle[][] addRoom(int x, int y, RoomStyle[][] maze){
		
		Direction d;
		
		while (hasEmptyNeighbour(x, y, maze)){
			
			d = Direction.random();
			
			if ((d == Direction.SOUTH) && (y < maze.length - 1) && (maze[y + 1][x] == RoomStyle.NONE)){
				
				maze[y + 1][x] = RoomStyle.getStyle(0x0f00);
				maze[y][x] = RoomStyle.getStyle(maze[y][x].getVal() | 0xf000);
				addRoom(x, y + 1, maze);
				
			} else if ((d == Direction.NORTH) && (y > 0) && (maze[y - 1][x] == RoomStyle.NONE)){
				
				maze[y - 1][x] = RoomStyle.getStyle(0xf000);
				maze[y][x] = RoomStyle.getStyle(maze[y][x].getVal() | 0x0f00);
				addRoom(x, y - 1, maze);
				
			} else if ((d == Direction.EAST) && (x < maze[0].length - 1) && (maze[y][x + 1] == RoomStyle.NONE)){
				
				maze[y][x + 1] = RoomStyle.getStyle(0x000f);
				maze[y][x] = RoomStyle.getStyle(maze[y][x].getVal() | 0x00f0);
				addRoom(x + 1, y, maze);
				
			} else if ((d == Direction.WEST) && (x > 0) && (maze[y][x - 1] == RoomStyle.NONE)){
				
				maze[y][x - 1] = RoomStyle.getStyle(0x00f0);
				maze[y][x] = RoomStyle.getStyle(maze[y][x].getVal() | 0x000f);
				addRoom(x - 1, y, maze);
				
			}
			
		}
		
		return maze;
	}
	
	private boolean hasEmptyNeighbour(int x, int y, RoomStyle[][] maze){
		
		if ((x > 0 && maze[y][x - 1] == RoomStyle.NONE) ||
			(y > 0 && maze[y - 1][x] == RoomStyle.NONE) ||
			(x < maze[0].length - 1 && maze[y][x + 1] == RoomStyle.NONE) ||
			(y < maze.length - 1 && maze[y + 1][x] == RoomStyle.NONE)){
			
			return true;
			
		}
		
		return false;
		
	}
	
	private enum RoomStyle{
		
		//Directions are where openings are
		//Values are hexadecimal so I can | them together
		//0xffff is snew
		
		NONE (0x0000),
		CORRIDOR_E_W (0x00ff),
		CORRIDOR_N_S (0xff00),
		DEAD_END_N (0x0f00),
		DEAD_END_S (0xf000),
		DEAD_END_E (0x00f0),
		DEAD_END_W (0x000f),
		CORNER_W_S (0xf00f),
		CORNER_E_S (0xf0f0),
		CORNER_W_N (0x0f0f),
		CORNER_E_N (0x0ff0),
		THREE_WAY_E_W_N (0x0fff),
		THREE_WAY_E_W_S (0xf0ff),
		THREE_WAY_E_N_S (0xfff0),
		THREE_WAY_W_N_S (0xff0f),
		FOUR_WAY (0xffff);
		
		int value;
		
		RoomStyle(int val){
			
			value = val;
			
		}
		
		public int getVal(){
			
			return value;
			
		}
		
		public static RoomStyle getStyle(int val){
			
			for (RoomStyle r : values()){
				
				if (r.getVal() == val){
					
					return r;
					
				}
			}
			
			return null;
			
		}
	}
	
}
