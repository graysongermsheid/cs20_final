package game;

import level.Level;
import java.util.Random;
import level.CollisionLayer;
import level.CollisionType;

public class EntityManager {

	private static CollisionLayer referenceCollisions;
	private static Random r = new Random();
	
	public static Entity spawnRandomLocation(EntityType t){
		
		if (t == EntityType.PLAYER){
			
			boolean foundLocation = false;
			int x = 0;
			int y = 0;
			
			while(!foundLocation){
				
				x = r.nextInt(referenceCollisions.getSize().width);
				y = r.nextInt(referenceCollisions.getSize().height);
				
				if (referenceCollisions.getType(x, y) == CollisionType.NONE){
					
					foundLocation = true;
					
				}
				
			}
			
			System.out.println("Spawning player at " + x * 16 + ", " + y * 16);
			return new Player(x * 16, y * 16, 100);
		}
		
		return null;
	}
	
	public static void setReferenceCollisions(CollisionLayer c){
		
		referenceCollisions = c;
		
	}
}
