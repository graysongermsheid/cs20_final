package game;

import level.CollisionType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import level.AABB;
import level.CollisionLayer;
import resources.Animation;
import resources.ResourceManager;
import resources.SpriteFont;

public abstract class LivingEntity extends Entity {

	protected boolean alive;
	protected int health;
	protected Speed speed;
	protected Direction direction; //values correspond to animation states
	protected Animation[] animations; //0-3 are normal, 4-7 are hurt
	protected static CollisionLayer collisions;
	protected double invulnerableTimer;

	public LivingEntity(int x, int y, int width, int height, String spriteSheet, int health) {
		
		super(x, y, width, height, spriteSheet);
		this.health = health;
		alive = true;
		speed = new Speed();
		direction = Direction.SOUTH;
		entitiesCollided = new ArrayList<Entity>();
		//hitBox = new AABB(boundingBox.getX() + 2, boundingBox.getY(), 13, boundingBox.getHeight() - 1);
		
		animations = new Animation[4];
		animations[0] = new Animation(sourceSprites.getRange(0, 2), 0.25d);
		animations[0].setRange(1, 2);
		animations[1] = new Animation(sourceSprites.getRange(3, 4), 0.3d);
		animations[2] = new Animation(sourceSprites.getRange(6, 8), 0.25d);
		animations[2].setRange(1, 2);
		animations[3] = new Animation(sourceSprites.getRange(10, 11), 0.3d);
		
	}

	public static void setLevelCollisionLayer(CollisionLayer c){
		
		collisions = c;
		
	}
	
	//Collide with terrain (call in update function)
	protected AABB collide(CollisionType c, AABB box){
		
		AABB newBox = new AABB(hitBox.getX() + speed.x, hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
		AABB newBoxX = new AABB(hitBox.getX() + speed.x, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
		
		if (!newBox.collides(box) || c == CollisionType.NONE || (speed.x == 0 && speed.y == 0)){
	
			return newBox;
			
		}
		
		while (newBox.collides(box)){
			
			if (newBoxX.collides(box) && speed.x != 0){
			
				int b = (speed.x < 0) ? 1 : -1;
				speed.x += b;
				newBoxX = new AABB(hitBox.getX() + speed.x, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
				
			} else if (newBoxX.collides(box) && speed.x == 0){
				
				if (box.getOverlapX(newBoxX) <= 8){
					
					newBoxX.setLocation(newBoxX.getX() + box.getOverlapX(newBoxX), hitBox.getY());
					
				} else {
					
					newBoxX.setLocation(newBoxX.getX() - newBoxX.getOverlapX(box), hitBox.getY());
					
				}
				
			}
		
			AABB newBoxY = new AABB(newBoxX.getX(), hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
		
			if (newBoxY.collides(box) && speed.y != 0){
			
				int b = (speed.y < 0) ? 1 : -1;
				speed.y += b;
				newBoxY = new AABB(hitBox.getX(), hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
			
			} else if (newBoxY.collides(box) && speed.y == 0){
				
				if (box.getOverlapY(newBoxY) <= 8){
					
					newBoxY.setLocation(newBoxX.getX(), newBoxY.getY() + box.getOverlapY(newBoxY));
					
				} else {
					
					newBoxY.setLocation(newBoxX.getX(), newBoxY.getY() - newBoxY.getOverlapY(box));
					
				}
				
			}
			
			newBox.setLocation(newBoxX.getX(), newBoxY.getY()); 
			
		}
		
		return newBox;
	}
	
	public void damage(int amount){
		
		if (invulnerableTimer > 0) { return; }
		
		ResourceManager.playSound("hit.wav");
		health -= (amount <= health) ? amount : health;
		invulnerableTimer = 2500;
		alive = health > 0;
		
	}
	
	public int getHealth(){
		
		return health;
		
	}
	
	public boolean getAlive(){
		
		return alive;
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		int x = (int)Math.floor(getCenter().x / 16.0);
		int y = (int)Math.floor(getCenter().y / 16.0);
		
		AABB newBox;
		
		newBox = collide(collisions.getType(x - 1, y - 1), collisions.getCollisionBox(x - 1, y - 1).shiftLocation(-1, -1));
		newBox = collide(collisions.getType(x, y - 1), collisions.getCollisionBox(x, y - 1).shiftLocation(0, -1));
		newBox = collide(collisions.getType(x + 1, y - 1), collisions.getCollisionBox(x + 1, y - 1).shiftLocation(0, -1));
		
		newBox = collide(collisions.getType(x - 1, y), collisions.getCollisionBox(x - 1, y).shiftLocation(-1, 0));
		newBox = collide(collisions.getType(x, y), collisions.getCollisionBox(x, y));
		newBox = collide(collisions.getType(x + 1, y), collisions.getCollisionBox(x + 1, y));
		
		newBox = collide(collisions.getType(x - 1, y + 1), collisions.getCollisionBox(x - 1, y + 1).shiftLocation(-1, 0));
		newBox = collide(collisions.getType(x, y + 1), collisions.getCollisionBox(x, y + 1));
		newBox = collide(collisions.getType(x + 1, y + 1), collisions.getCollisionBox(x + 1, y + 1));
	
		newBox = collide(collisions.getType(x - 1, y + 2), collisions.getCollisionBox(x - 1, y + 2).shiftLocation(-1, 0));
		newBox = collide(collisions.getType(x, y + 2), collisions.getCollisionBox(x, y + 2));
		newBox = collide(collisions.getType(x + 1, y + 2), collisions.getCollisionBox(x + 1, y + 2));
		
		hitBox = newBox;
		position.setLocation(position.x + speed.x, position.y + speed.y);
		
		Direction oldDirection = direction;
		
		if (speed.x > 0){
			
			direction = Direction.EAST;
			
		} else if (speed.x < 0){
			
			direction = Direction.WEST;
			
		}
		
		if (speed.y > 0){
			
			direction = Direction.SOUTH;
			
		} else if (speed.y < 0){
			
			direction = Direction.NORTH;
			
		}
		
		if ((speed.x != 0) || (speed.y != 0)){
			
			animations[direction.value()].update(elapsedMilliseconds);
			
		} else {

			if (!((direction == Direction.SOUTH) || (direction == Direction.NORTH))){
				
				direction = Direction.SOUTH;
				
			}
			animations[direction.value()].setCurrentFrame(0);
			
		}
		
		invulnerableTimer -= (invulnerableTimer - elapsedMilliseconds < 0) ? invulnerableTimer : elapsedMilliseconds;
		
	}
	
	public void draw(Graphics2D g, Point p){
		
		if (!(invulnerableTimer > 0 && (((((int)invulnerableTimer / 10) / 10) / 10) % 2 == 0))){ //flashes every 500 ms

			g.drawImage(animations[direction.value()].getCurrentFrame(), position.x - p.x, position.y - p.y, null);
			
		}
	}
}
