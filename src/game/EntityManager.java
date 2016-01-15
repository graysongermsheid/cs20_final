package game;

import level.Level;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import level.CollisionLayer;
import level.CollisionType;

public class EntityManager {

	private CollisionLayer referenceCollisions;
	private Random r = new Random();
	private ArrayList<BadGuy> entities = new ArrayList<BadGuy>();
	
	public Entity spawnRandomLocation(EntityType t){
		
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
	
	public void spawn(EntityType t, Point p){
		
		if (t == EntityType.MONSTER){
			
			entities.add(new BadGuy(p.x, p.y));
			
		}
		
	}
	
	public void setReferenceCollisions(CollisionLayer c){
		
		referenceCollisions = c;
		
	}
	
	public void update(double elapsedMilliseconds){
		
		for (BadGuy b : entities){
			
			b.update(elapsedMilliseconds);
			
		}
		
	}
	
	public void draw(Graphics2D g, Point p){
		
		for (BadGuy b : entities){
			
			b.draw(g, p);
			
		}
		
	}
}
