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
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Entity spawnRandomLocation(EntityType t){
		
		if (t == EntityType.PLAYER){
			
			boolean foundLocation = false;
			int x = 0;
			int y = 0;
			Player p = null;
			
			while(!foundLocation){
				
				x = r.nextInt(referenceCollisions.getSize().width);
				y = r.nextInt(referenceCollisions.getSize().height);
				p = new Player(x * 16, y * 16, 15);
				
				if (referenceCollisions.getType(x, y) == CollisionType.NONE){
					
					foundLocation = true;
					
				}
				
				//MAKE THIS ENTITY AGNOSTIC
				if ((p.getHitBox().collides(referenceCollisions.getCollisionBox(x - 1, y - 1)) && referenceCollisions.getType(x - 1, y - 1) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x, y - 1)) && referenceCollisions.getType(x, y - 1) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x + 1, y - 1)) && referenceCollisions.getType(x + 1, y - 1) == CollisionType.WALL) ||
					
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x - 1, y)) && referenceCollisions.getType(x - 1, y) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x + 1, y)) && referenceCollisions.getType(x + 1, y) == CollisionType.WALL) ||
					
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x - 1, y + 1)) && referenceCollisions.getType(x - 1, y + 1) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x, y + 1)) && referenceCollisions.getType(x, y + 1) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x + 1, y + 1)) && referenceCollisions.getType(x + 1, y + 1) == CollisionType.WALL) ||
					
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x - 1, y + 2)) && referenceCollisions.getType(x - 1, y + 2) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x, y + 2)) && referenceCollisions.getType(x, y + 2) == CollisionType.WALL) ||
					(p.getHitBox().collides(referenceCollisions.getCollisionBox(x + 1, y + 2)) && referenceCollisions.getType(x + 1, y + 2) == CollisionType.WALL)){
					
					foundLocation = false;
					
				}
				
			}
			
			System.out.println("Spawning player at " + x * 16 + ", " + y * 16);
			entities.add(p);
			return p;
		} else {
			
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
			Snake p = new Snake(x * 16, y * 16);
			entities.add(p);
			
		}
		
		return null;
	}
	
	public void spawn(EntityType t, Point p){
		
		if (t == EntityType.MONSTER){
			
			entities.add(new Snake(p.x, p.y));
			
		}
		
	}
	
	public void setReferenceCollisions(CollisionLayer c){
		
		referenceCollisions = c;
		
	}
	
	public void update(double elapsedMilliseconds){
		
		for (Entity b : entities){
			
			b.update(elapsedMilliseconds);
			
			for (Entity c : entities){
				
				if (c.getHitBox().collides(b.getHitBox())){
					
					b.collide(c);
					
				} else {
					
					b.removeCollision(c);
					
				}
				
			}
			
		}
		
	}
	
	public void draw(Graphics2D g, Point p){
		
		boolean sorted = false;
		
		while (!sorted){

			sorted = true;
			
			for (int i = 0; i < entities.size() - 1; i++){
				
				if (entities.get(i).getFarLocation().y > entities.get(i + 1).getFarLocation().y){
					
					System.out.println("i: " + entities.get(i).getFarLocation().y + 
									   "i + 1: " + entities.get(i + 1).getFarLocation().y);
					
					sorted = false;
					Entity e = entities.get(i + 1);
					entities.set(i + 1, entities.get(i));
					entities.set(i, e);
					
				}
				
			}
		}
		
		for (Entity e : entities){
			
			e.draw(g, p);
			
		}
		
	}
}
