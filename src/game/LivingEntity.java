package game;

import level.CollisionType;

import java.awt.Graphics2D;

import level.AABB;
import resources.Animation;

public abstract class LivingEntity extends Entity {

	protected boolean alive;
	protected int health;
	protected Speed speed;
	protected Direction direction; //values correspond to animation states
	protected Animation[] animations;

	public LivingEntity(int x, int y, int width, int height, String spriteSheet, int health) {
		
		super(x, y, width, height, spriteSheet);
		alive = true;
		speed = new Speed();
		direction = Direction.SOUTH;
		
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
		
		
		
	}
	
	@Override
	public void draw(Graphics2D g){
		
		
		
	}

}
