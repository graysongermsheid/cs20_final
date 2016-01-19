package level.worldgen;

import level.Level;
import game.Direction;

public class DungeonGenerator {

	public Level createLevel(){
		
		return null;
		
	}
	
	private RoomStyle[][] createMaze(int width, int height){
		
		RoomStyle[][] rooms = new RoomStyle[height][width];
		
		return null;
		
	}
	
	private RoomStyle[][] addRoom(int x, int y, RoomStyle[][] maze, Direction d){
		
		
		
		return null;
		
	}
	
	private enum RoomStyle{
		
		//Directions are where openings are
		//Values are hexadecimal so I can & them together
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
