package game;

public class CollisionLayer {
	
	private CollisionType[][] collisionTypes;

	public CollisionLayer(int width, int height){

		collisionTypes = new CollisionType[height][width];

	}

	public void set(int x, int y, CollisionType type){

		collisionTypes[y][x] = type;

	}
}