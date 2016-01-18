package game;

import java.util.ArrayList;
import java.util.Random;

import level.AABB;

import java.awt.Graphics2D;

public class Snake extends LivingEntity implements Monster {

	private Speed desiredSpeed;
	private int attackDamage;
	
	public Snake(int x, int y) {
		
		super(x + 4, y, 16, 11, "snake.png", 30);
		hitBox = new AABB(x + 4, y + 6, 15, 10);
		Random r = new Random();
		desiredSpeed = new Speed();
		while ((desiredSpeed.x == 0) && (desiredSpeed.y == 0)){
			
			desiredSpeed.x = r.nextInt(3) - 1;
			desiredSpeed.y = r.nextInt(3) - 1;
			System.out.println("Set snake spedd: " + desiredSpeed.x + ", " + desiredSpeed.y + " (@" + hitBox.getX() + ", " + hitBox.getY() + ")");
		}
		attackDamage = 10;
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

		//if (!entitiesCollided.contains(e)){
			
			entitiesCollided.add(e);
			
			if (e.getType() == EntityType.PLAYER){
				
				((Player)e).damage(attackDamage);
				
			}
			
		//}
		
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
		// TODO Auto-generated method stub
		return null;
	}
}
