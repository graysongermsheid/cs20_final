package game;

import java.awt.Graphics2D;

import input.InputHandler;

public class Player extends LivingEntity{
	
	public Player(int x, int y, int health) {
		
		super(x, y, 16, 16, "player.png", health);

	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}

	public void processInput(){
		
		if (InputHandler.KEY_UP_PRESSED && !InputHandler.KEY_DOWN_PRESSED){
			
			speed.setYSpeed(-2);
			
		} else if (InputHandler.KEY_DOWN_PRESSED && !InputHandler.KEY_UP_PRESSED){
			
			speed.setYSpeed(2);
			
		} else {
			
			speed.setYSpeed(0);
			
		}
		
		if (InputHandler.KEY_LEFT_PRESSED && !InputHandler.KEY_RIGHT_PRESSED){
			
			speed.setXSpeed(-2);
			
		} else if (InputHandler.KEY_RIGHT_PRESSED && !InputHandler.KEY_LEFT_PRESSED){
			
			speed.setXSpeed(2);
			
		} else {
			
			speed.setXSpeed(0);
			
		}
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		super.update(elapsedMilliseconds);
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
}
