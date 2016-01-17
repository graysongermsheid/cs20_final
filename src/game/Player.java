package game;

import java.awt.Graphics2D;

import input.InputHandler;
import resources.ResourceManager;

public class Player extends LivingEntity{
	
	private double footstepTimer;
	private double footstepGap;
	
	public Player(int x, int y, int health) {
		
		super(x + 4, y, 13, 11, "player.png", health);

		footstepGap = 250d;
		footstepTimer = 0d;
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}

	public void processInput(){
		
		if (InputHandler.KEY_UP_PRESSED && !InputHandler.KEY_DOWN_PRESSED){
			
			speed.setYSpeed(-1);
			
		} else if (InputHandler.KEY_DOWN_PRESSED && !InputHandler.KEY_UP_PRESSED){
			
			speed.setYSpeed(1);
			
		} else {
			
			speed.setYSpeed(0);
			
		}
		
		if (InputHandler.KEY_LEFT_PRESSED && !InputHandler.KEY_RIGHT_PRESSED){
			
			speed.setXSpeed(-1);
			
		} else if (InputHandler.KEY_RIGHT_PRESSED && !InputHandler.KEY_LEFT_PRESSED){
			
			speed.setXSpeed(1);
			
		} else {
			
			speed.setXSpeed(0);
			
		}
		
		if (speed.x == 0 && speed.y != 0){
			
			footstepGap = 300;
			
		} else {
			
			footstepGap = 250;
			
		}
	}
	
	@Override
	public void damage(int amount){
		
		super.damage(amount);
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		super.update(elapsedMilliseconds);
		
		footstepTimer += elapsedMilliseconds;
		
		if (!alive){
			
			ResourceManager.playSound("die.wav");
			gamescreen.ScreenManager.switchCurrentScreen(new gamescreen.GameOver(100, 1, "SNAKE"));
			
		}
		
		if (footstepTimer > footstepGap && (speed.x != 0 || speed.y != 0)){
			
			footstepTimer = 0d;
			ResourceManager.playSound("footstep.wav");
			
		}
		
	}
	
	@Override
	public EntityType getType(){
		
		return EntityType.PLAYER;
		
	}
}
