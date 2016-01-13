package game;

import level.CollisionType;

import java.awt.Graphics2D;
import java.awt.Point;

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
		hitBox = new AABB(boundingBox.getX() + 2, boundingBox.getY(), 11, boundingBox.getHeight());
		
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
	protected void collide(CollisionType c, AABB box){
		
		AABB newPosition = new AABB(hitBox.getX() + speed.x, hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
		
		if (!box.collides(newPosition) || c == CollisionType.NONE){
			
			hitBox = newPosition;
			
			switch(direction){
			
			case SOUTH:
				
				boundingBox.setLocation(hitBox.getX() - 2, hitBox.getY());
				
				break;
			case NORTH:
				
				boundingBox.setLocation(hitBox.getX() - 2, hitBox.getY());
				
				break;
			case EAST:
				
				boundingBox.setLocation(hitBox.getX() - 1, hitBox.getY());
				
				break;
			case WEST:
				
				boundingBox.setLocation(hitBox.getX() - 3, hitBox.getY());
				
				break;
		}
			
			return;
			
		}
		
		System.out.println("COLLISION");
		
		while (newPosition.getOverlapX(box) > 0){
			
			speed.setXSpeed(speed.x - 1 < 0 ? 0 : speed.x - 1);
			newPosition = new AABB(hitBox.getX() + speed.x, hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
			
		}
		
		while (newPosition.getOverlapY(box) > 0){
			
			speed.setYSpeed(speed.y - 1 < 0 ? 0 : speed.y - 1);
			newPosition = new AABB(hitBox.getX() + speed.x, hitBox.getY() + speed.y, hitBox.getWidth(), hitBox.getHeight());
			
		}
		
		hitBox = newPosition;
		
		switch(direction){
		
			case SOUTH:
				
				boundingBox.setLocation(hitBox.getX() - 2, hitBox.getY());
				
				break;
			case NORTH:
				
				boundingBox.setLocation(hitBox.getX() - 2, hitBox.getY());
				
				break;
			case EAST:
				
				boundingBox.setLocation(hitBox.getX() - 1, hitBox.getY());
				
				break;
			case WEST:
				
				boundingBox.setLocation(hitBox.getX() - 3, hitBox.getY());
				
				break;
		}
	}
	
	public void damage(int amount){
		
		health -= amount <= health ? amount : health;
		alive = health > 0;
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		Direction oldDirection = direction;
		
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
		
		//AABB newPos = new AABB(boundingBox.getLocation().x + speed.x, boundingBox.getLocation().y + speed.y, boundingBox.getSize().width, boundingBox.getSize().height);
		int x = boundingBox.getLocation().x / 16;
		int y = boundingBox.getLocation().y / 16;
		
		collide(collisions.getType(x - 1, y - 1), collisions.getCollisionBox(x - 1, y - 1));
		collide(collisions.getType(x, y - 1), collisions.getCollisionBox(x, y - 1));
		collide(collisions.getType(x + 1, y - 1), collisions.getCollisionBox(x + 1, y - 1));
		
		collide(collisions.getType(x - 1, y), collisions.getCollisionBox(x - 1, y));
		collide(collisions.getType(x, y), collisions.getCollisionBox(x, y));
		collide(collisions.getType(x + 1, y), collisions.getCollisionBox(x + 1, y));
		
		collide(collisions.getType(x - 1, y + 1), collisions.getCollisionBox(x - 1, y + 1));
		collide(collisions.getType(x, y + 1), collisions.getCollisionBox(x, y + 1));
		collide(collisions.getType(x + 1, y + 1), collisions.getCollisionBox(x + 1, y + 1));
	
	}
	
	public void draw(Graphics2D g, Point p){
		
		g.setColor(java.awt.Color.BLACK);
		g.fillRect(boundingBox.getLocation().x - p.x, boundingBox.getLocation().y - p.y, boundingBox.getWidth(), boundingBox.getHeight());
		
		switch(direction){
		
			case SOUTH:
				g.setColor(java.awt.Color.YELLOW);
				break;
			case NORTH:
				g.setColor(java.awt.Color.GREEN);
				break;
			case EAST:
				g.setColor(java.awt.Color.BLUE);
				break;
			case WEST:
				g.setColor(java.awt.Color.RED);
				break;
		
		}
		
		g.drawRect(hitBox.getX() - p.x, hitBox.getY() - p.y, hitBox.getWidth(), hitBox.getHeight());
		g.drawImage(animations[direction.value()].getCurrentFrame(), boundingBox.getLocation().x - p.x, boundingBox.getLocation().y - p.y, null);
		
	}

}
