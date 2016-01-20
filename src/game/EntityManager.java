package game;

import level.Level;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import level.AABB;
import level.CollisionLayer;
import level.CollisionType;

public class EntityManager {

	private CollisionLayer referenceCollisions;
	private Random r = new Random();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Entity spawnRandomLocation(EntityType t){
		
		Entity e = null;
		boolean foundLocation = false;
		int x = 0;
		int y = 0;
		
		while (!foundLocation){
			
			x = r.nextInt(referenceCollisions.getSize().width);
			y = r.nextInt(referenceCollisions.getSize().height);
			
			if (t == EntityType.PLAYER){
				
				e = new Player(x * 16, y * 16, 100);
				
			} else if (t == EntityType.MONSTER){
				
				MonsterType m = MonsterType.random();
				
				switch (m){
				
				case GUARD:
					e = new Guard(x * 16, y * 16);
					break;
				case SNAKE:
				default:
					e = new Snake(x * 16, y * 16);
					break;
				}
				
			}
			
			if (referenceCollisions.getType(x, y) == CollisionType.NONE &&
				!checkCollisions(e.getHitBox(), x, y)){
				
				foundLocation = true;
				
			}
			
		}
		
		System.out.println("Spawning entity at: [" + x * 16 + ", " + y * 16 + "]");
		entities.add(e);
		return e;
	}
	
	public void spawnMonsters(int amount){
		
		for (int i = 0; i < amount; i++){
			
			spawnRandomLocation(EntityType.MONSTER);
			
		}
		
	}
	
	private boolean checkCollisions(AABB a, int x, int y){
		
		if ((a.collides(referenceCollisions.getCollisionBox(x - 1, y - 1)) && referenceCollisions.getType(x - 1, y - 1) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x, y - 1)) && referenceCollisions.getType(x, y - 1) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x + 1, y - 1)) && referenceCollisions.getType(x + 1, y - 1) == CollisionType.WALL) ||
				
			(a.collides(referenceCollisions.getCollisionBox(x - 1, y)) && referenceCollisions.getType(x - 1, y) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x + 1, y)) && referenceCollisions.getType(x + 1, y) == CollisionType.WALL) ||
				
			(a.collides(referenceCollisions.getCollisionBox(x - 1, y + 1)) && referenceCollisions.getType(x - 1, y + 1) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x, y + 1)) && referenceCollisions.getType(x, y + 1) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x + 1, y + 1)) && referenceCollisions.getType(x + 1, y + 1) == CollisionType.WALL) ||
				
			(a.collides(referenceCollisions.getCollisionBox(x - 1, y + 2)) && referenceCollisions.getType(x - 1, y + 2) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x, y + 2)) && referenceCollisions.getType(x, y + 2) == CollisionType.WALL) ||
			(a.collides(referenceCollisions.getCollisionBox(x + 1, y + 2)) && referenceCollisions.getType(x + 1, y + 2) == CollisionType.WALL)){
				
			return true;
				
		}
		
		return false;
		
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
	
	public Entity[] getEntities(){
		
		Entity[] list = new Entity[entities.size()];
		list = entities.toArray(list);
		
		return list;
		
	}
}
