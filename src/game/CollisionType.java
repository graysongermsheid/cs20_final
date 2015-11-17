package game;

public enum CollisionType {
	
	WALL (1),
	DOOR (2),
	DAMAGE (3);

	int value;

	CollisionType(int value){

		this.value = value;

	}
}