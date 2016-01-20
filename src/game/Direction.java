package game;

import java.util.Random;

public enum Direction {
	
	SOUTH (0),
	EAST (1),
	NORTH (2),
	WEST (3);
	
	int val;
	
	Direction(int val){
		
		this.val = val;
		
	}
	
	public int value(){
		
		return val;
		
	}
	
	public static Direction random(){
		
		Random r = new Random();
		switch(r.nextInt(4)){
		
			case 0:
				return SOUTH;
			case 1:
				return NORTH;
			case 2:
				return EAST;
			default:
				return WEST;
		
		}
		
	}
}
