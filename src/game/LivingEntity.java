package game;

import level.CollisionType;

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
	protected Animation[] animations;
	protected ArrayList<Entity> collidedEntities; //list of entities this entity is already collided with
	protected static CollisionLayer collisions;

	public LivingEntity(int x, int y, int width, int height, String spriteSheet, int health) {
		
		super(x, y, width, height, spriteSheet);
		alive = true;
		speed = new Speed();
		direction = Direction.SOUTH;
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
	
	@Override
	public abstract void collide(Entity e);
	
	//Collide with terrain (call in update function)
	protected void collide(CollisionType c, AABB box){
		
		AABB newBox = new AABB(hitBox.getX() + speed.x, hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
		AABB newBoxX = new AABB(hitBox.getX() + speed.x, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
		
		if (!newBox.collides(box) || c == CollisionType.NONE || (speed.x == 0 && speed.y == 0)){
	
			return;
			
		}
		
		while (newBox.collides(box)){
			
			if (newBoxX.collides(box) && speed.x != 0){
			
				int b = (speed.x < 0) ? 1 : -1;
				speed.x += b;
				newBoxX = new AABB(hitBox.getX() + speed.x, hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
				
			}
		
			AABB newBoxY = new AABB(newBoxX.getX(), hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
		
			if (newBoxY.collides(box) && speed.y != 0){
			
				int b = (speed.y < 0) ? 1 : -1;
				speed.y += b;
				newBoxY = new AABB(hitBox.getX(), hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
			
			}
			
			newBox.setLocation(hitBox.getX() + speed.x, hitBox.getY() + speed.y); 
			
		}
	}
	
	public void damage(int amount){
		
		health -= amount <= health ? amount : health;
		alive = health > 0;
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		int x = (int)Math.floor(getCenter().x / 16.0);
		int y = (int)Math.floor(getCenter().y / 16.0);
		
		
		collide(collisions.getType(x - 1, y - 1), collisions.getCollisionBox(x - 1, y - 1).shiftLocation(-1, -1));
		collide(collisions.getType(x, y - 1), collisions.getCollisionBox(x, y - 1).shiftLocation(0, -1));
		collide(collisions.getType(x + 1, y - 1), collisions.getCollisionBox(x + 1, y - 1).shiftLocation(0, -1));
		
		collide(collisions.getType(x - 1, y), collisions.getCollisionBox(x - 1, y).shiftLocation(-1, 0));
		collide(collisions.getType(x, y), collisions.getCollisionBox(x, y));
		collide(collisions.getType(x + 1, y), collisions.getCollisionBox(x + 1, y));
		
		collide(collisions.getType(x - 1, y + 1), collisions.getCollisionBox(x - 1, y + 1).shiftLocation(-1, 0));
		collide(collisions.getType(x, y + 1), collisions.getCollisionBox(x, y + 1));
		collide(collisions.getType(x + 1, y + 1), collisions.getCollisionBox(x + 1, y + 1));
	
		collide(collisions.getType(x - 1, y + 2), collisions.getCollisionBox(x - 1, y + 2).shiftLocation(-1, 0));
		collide(collisions.getType(x, y + 2), collisions.getCollisionBox(x, y + 2));
		collide(collisions.getType(x + 1, y + 2), collisions.getCollisionBox(x + 1, y + 2));
		
		hitBox.setLocation(hitBox.getX() + speed.x, hitBox.getY() + speed.y);
		
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
		
	}
	
	public void draw(Graphics2D g, Point p){
		
		g.drawImage(animations[direction.value()].getCurrentFrame(), hitBox.getLocation().x - p.x - 1, hitBox.getLocation().y - p.y - 4, null);
		
	}

}
