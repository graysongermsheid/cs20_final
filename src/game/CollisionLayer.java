package game;

public class CollisionLayer {
	
	private CollisionType[][] collisionTypes;

	public CollisionLayer(int width, int height){

		collisionTypes = new CollisionType[height][width];

	}

	public void addCollision(CollisionType type, int x, int y){

		collisionTypes[y][x] = type;

	}

	public void finalize(){

		for (int i = 0; i < collisionTypes.length; i++){

			for (int j = 0; j < collisionTypes[0].length; j++){

				if (collisionTypes[i][j] == null){

					collisionTypes[i][j] = CollisionType.NONE;

				}
			}
		}
	}

	/*public void processCollision(Entity e){



	}*/
}