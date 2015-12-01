package level;

public enum CollisionType {
	
	NONE (0x0),
	WALL (0xF),
	DOOR (0xD);

	int value;

	public CollisionType(int value){

		this.value = value;

	}
}