package game;

import level.CollisionType;
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
	public void collide(Entity e) {

		
		
	}
	
	//Collide with terrain
	public void collide(CollisionType c, AABB box){
		
		
		
	}
	
	public void damage(int amount){
		
		health -= amount <= health ? amount : health;
		alive = health > 0;
		
	}

}
