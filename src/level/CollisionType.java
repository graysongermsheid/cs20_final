package level;

public enum CollisionType {
	
	NONE (0x0),
	WALL (0xF),
	DOOR (0xD);

	int val;

	CollisionType(int value){

		this.val = value;

	}
	
	public int value(){
		
		return val;
		
	}
}