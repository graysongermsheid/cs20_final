package game;

import java.util.Random;
import java.awt.Graphics2D;

public class Snake extends LivingEntity {

	private Speed desiredSpeed;
	
	public Snake(int x, int y) {
		
		super(x + 4, y, 16, 11, "character.png", 100);
		Random r = new Random();
		desiredSpeed = new Speed();
		while ((desiredSpeed.x == 0) && (desiredSpeed.y == 0)){
			
			desiredSpeed.x = r.nextInt(3) - 1;
			desiredSpeed.y = r.nextInt(3) - 1;
			System.out.println("Set snake spedd: " + desiredSpeed.x + ", " + desiredSpeed.y + " (@" + hitBox.getX() + ", " + hitBox.getY() + ")");
		}
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

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

}
