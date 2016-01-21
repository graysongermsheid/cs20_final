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
				
				e = new Player(x * 16, y * 16, 50);
				
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
			} else if (t == EntityType.COIN){
				
				e = new Coin(x * 16, y * 16, r.nextInt(25) + 1);
				
			} else if (t == EntityType.HEAL){
				
				e = new HealingPotion(x * 16, y * 16, r.nextInt(25) + 1);
				
			} else if (t == EntityType.DOOR){
				
				e = new Door(x * 16, y * 16);
				
			}
			
			if (referenceCollisions.getType(x, y) == CollisionType.NONE &&
				!checkCollisions(e.getHitBox(), x, y)){
				
				foundLocation = true;
				
			}
			
		}
		
		entities.add(e);
		return e;
	}
	
	public void spawnEntities(EntityType e, int amount){
		
		for (int i = 0; i < amount; i++){
			
			spawnRandomLocation(e);
			
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
		
		for (int i = 0; i < entities.size(); i++){
			
			if (entities.get(i).disposalFlag){
				
				entities.remove(i);
				if (i >= entities.size()){
					
					break;
					
				}
				
			}
			entities.get(i).update(elapsedMilliseconds);
		
			for (Entity c : entities){
				
				if (c.getHitBox().collides(entities.get(i).getHitBox())){
					
					entities.get(i).collide(c);
					
				} else {
					
					entities.get(i).removeCollision(c);
					
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
	
	public void clearEntities(){
		
		entities = new ArrayList<Entity>();
		
	}
}
