package game;

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
	
}
