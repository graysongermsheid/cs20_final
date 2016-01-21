package game;

import level.AABB;
import java.util.Random;

public class Guard extends LivingEntity implements Monster {

	private double walkTimer;
	private double walkCooldown;
	private Direction desiredDirection;
	private Random r;
	
	public Guard(int x, int y){
		
		super(x, y, 16, 16, "guard.png", 50);
		r = new Random();
		hitBox = new AABB(x + 1, y + 6, 13, 9);
		walkTimer = 1000d;
		walkCooldown = (double)r.nextInt(3000);
		
	}
	public void collide(Entity e) {
	
		entitiesCollided.add(e);
			
		if (e.getType() == EntityType.PLAYER){
				
			((Player)e).damage(25, this);
				
		}
		
	}

	public void think(){
		
		speed.setSpeed(0, 0);
		
		if (walkTimer > 0 && walkCooldown <= 0){
	
			if (desiredDirection == null){

				desiredDirection = Direction.random();
				
			}
			
			switch (desiredDirection){
			
				case SOUTH:
					speed.setSpeed(0, 3);
					break;
				case NORTH:
					speed.setSpeed(0, -3);
					break;
				case EAST:
					speed.setSpeed(3, 0);
					break;
				case WEST:
				default:
					speed.setSpeed(-3, 0);
					break;
			}
		} else if (walkTimer <= 0 && walkCooldown <= 0){
			
			desiredDirection = null;
			walkTimer = 1000d;
			walkCooldown = (double)r.nextInt(3001) + 1000;
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {

		super.update(elapsedMilliseconds);
		walkCooldown -= elapsedMilliseconds;

		if (walkCooldown <= 0) { walkTimer -= elapsedMilliseconds; }
		
		think();
		
	}

	@Override
	public EntityType getType(){
		
		return EntityType.MONSTER;
		
	}
	@Override
	public MonsterType getMonsterType() {

		return MonsterType.GUARD;
		
	}

}
