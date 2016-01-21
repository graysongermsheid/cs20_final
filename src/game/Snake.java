package game;

import java.util.ArrayList;
import java.util.Random;

import level.AABB;

import java.awt.Graphics2D;

public class Snake extends LivingEntity implements Monster {

	private Speed desiredSpeed;
	private int attackDamage;
	
	public Snake(int x, int y) {
		
		super(x, y, 16, 16, "snake.png", 30);
		hitBox = new AABB(x, y + 6, 15, 9);
		Random r = new Random();
		desiredSpeed = new Speed();
		while ((desiredSpeed.x == 0) && (desiredSpeed.y == 0)){
			
			desiredSpeed.x = r.nextInt(3) - 1;
			desiredSpeed.y = r.nextInt(3) - 1;
			
		}
		attackDamage = 10;
		
	}

	@Override
	public void collide(Entity e) {
			
		entitiesCollided.add(e);
			
		if (e.getType() == EntityType.PLAYER){
				
			((Player)e).damage(attackDamage, this);
				
		}
		
	}

	public void think(){
		
		if (speed.x != desiredSpeed.x){
			
			desiredSpeed.x *= -1;
			speed.x = desiredSpeed.x;
			
		}
		
		if (speed.y != desiredSpeed.y){
			
			desiredSpeed.y *= -1;
			speed.y = desiredSpeed.y;
			
		}
	}
	
	@Override
	public void update(double elapsedMilliseconds) {

		super.update(elapsedMilliseconds);
		think();
		
	}

	@Override
	public EntityType getType(){
		
		return EntityType.MONSTER;
		
	}
	
	@Override
	public MonsterType getMonsterType() {

		return MonsterType.SNAKE;
		
	}
}
