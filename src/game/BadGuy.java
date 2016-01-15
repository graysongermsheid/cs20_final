package game;

import java.awt.Graphics2D;

public class BadGuy extends LivingEntity {

	private int desiredSpeed;
	
	public BadGuy(int x, int y) {
		
		super(x + 4, y, 16, 11, "character.png", 100);
		desiredSpeed = 1;
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

	}

	public void think(){
		
		if (speed.x != desiredSpeed){
			
			desiredSpeed = (-1 * desiredSpeed);
			speed.x = desiredSpeed;
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {

		super.update(elapsedMilliseconds);
		think();
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
