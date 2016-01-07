package game;

import level.CollisionType;

import java.awt.Graphics2D;

import level.AABB;
import level.CollisionLayer;
import resources.Animation;

public abstract class LivingEntity extends Entity {

	protected boolean alive;
	protected int health;
	protected Speed speed;
	protected Direction direction; //values correspond to animation states
	protected Animation[] animations;
	protected static CollisionLayer collisions;

	public LivingEntity(int x, int y, int width, int height, String spriteSheet, int health) {
		
		super(x, y, width, height, spriteSheet);
		alive = true;
		speed = new Speed();
		direction = Direction.SOUTH;
		
		animations = new Animation[4];
		animations[0] = new Animation(sourceSprites.getRange(0, 2), 0.5d);
		animations[1] = new Animation(sourceSprites.getRange(3, 5), 0.5d);
		animations[2] = new Animation(sourceSprites.getRange(6, 8), 0.5d);
		animations[3] = new Animation(sourceSprites.getRange(9, 11), 0.5d);
		
	}

	public static void setLevelCollisionLayer(CollisionLayer c){
		
		collisions = c;
		
	}
	
	@Override
	public abstract void collide(Entity e);
	
	//Collide with terrain (call in update function)
	protected void collide(CollisionType c, AABB box, AABB newPosition){
		
		if (!box.collides(newPosition)){
			
			return;
			
		}
		
		if (newPosition.getOverlapX(box) > 0){
			
			newPosition.setLocation(box.getLocation().x - newPosition.getSize().width, newPosition.getLocation().y);
			speed.setXSpeed(0);
			
		}
		
		if (newPosition.getOverlapY(box) > 0){
			
			newPosition.setLocation(newPosition.getLocation().x, box.getLocation().y - newPosition.getSize().height);
			speed.setYSpeed(0);
			
		}
		
	}
	
	public void damage(int amount){
		
		health -= amount <= health ? amount : health;
		alive = health > 0;
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		if (speed.y > 0){
			
			direction = Direction.SOUTH;
			
		} else if (speed.y < 0){
			
			direction = Direction.NORTH;
			
		} else if (speed.x > 0){
			
			direction = Direction.EAST;
			
		} else if (speed.x < 0){
			
			direction = Direction.WEST;
			
		}
		
		if ((speed.x != 0) || (speed.y != 0)){
			
			animations[direction.value()].update(elapsedMilliseconds);
			
		} else {
			
			animations[direction.value()].setCurrentFrame(0);
			
		}
		
		AABB newPos = new AABB(boundingBox.getLocation().x + speed.x, boundingBox.getLocation().y + speed.y, boundingBox.getSize().width, boundingBox.getSize().height);
		int x = boundingBox.getLocation().x / 16;
		int y = boundingBox.getLocation().y / 16;
		
		collide(collisions.getType(x - 1, y - 1), collisions.getCollisionBox(x - 1, y - 1), newPos);
		collide(collisions.getType(x, y - 1), collisions.getCollisionBox(x, y - 1), newPos);
		collide(collisions.getType(x + 1, y - 1), collisions.getCollisionBox(x + 1, y - 1), newPos);
		
		collide(collisions.getType(x - 1, y), collisions.getCollisionBox(x - 1, y), newPos);
		collide(collisions.getType(x, y), collisions.getCollisionBox(x, y), newPos);
		collide(collisions.getType(x + 1, y), collisions.getCollisionBox(x + 1, y), newPos);
		
		collide(collisions.getType(x - 1, y + 1), collisions.getCollisionBox(x - 1, y + 1), newPos);
		collide(collisions.getType(x, y + 1), collisions.getCollisionBox(x, y + 1), newPos);
		collide(collisions.getType(x + 1, y + 1), collisions.getCollisionBox(x + 1, y + 1), newPos);
		
		boundingBox = newPos;
	}
	
	@Override
	public void draw(Graphics2D g){
		
		g.drawImage(animations[direction.value()].getCurrentFrame(), boundingBox.getLocation().x, boundingBox.getLocation().y, null);
		
	}

}
