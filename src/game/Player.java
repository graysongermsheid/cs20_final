package game;

import java.awt.Graphics2D;
import java.awt.Point;

import input.InputHandler;
import level.AABB;
import resources.ResourceManager;
import resources.SpriteFont;

public class Player extends LivingEntity{
	
	private double footstepTimer;
	private double footstepGap;
	private SpriteFont scoreFont;
	
	public Player(int x, int y, int health) {
		
		super(x, y, 16, 16, "player.png", health);
		hitBox = new AABB(x + 1, y + 6, 13, 9);
		
		footstepGap = 250d;
		footstepTimer = 0d;
	
		scoreFont = ResourceManager.getFont("font_small.png");
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
		
		if (speed.x == 0 && speed.y != 0){
			
			footstepGap = 750;
			
		} else {
			
			footstepGap = 750;
			
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
			//ResourceManager.playSound("footstep.wav");
			
		}
		
	}
	
	@Override
	public void draw(Graphics2D g, Point p){
		
		super.draw(g, p);
		
		//DRAW HP BAR
		g.setColor(java.awt.Color.BLACK);
		g.fillRect(2, 134, 64, 8);
		g.setColor(java.awt.Color.RED);
		g.fillRect(2, 134, (int)((health / 100.0) * 64), 8);
		g.drawImage(ResourceManager.getImage("hp_small.png"), 2, 134, null);
		
		//DRAW SCORE & MONEY
		String score = "SCORE:" + ScoreTracker.score + " $" + ScoreTracker.goldCollected;
		scoreFont.setBackgroundColor(java.awt.Color.ORANGE);
		scoreFont.setColor(java.awt.Color.YELLOW);
		scoreFont.drawShadowedText(score, 256 - scoreFont.getStringSize(score).width - 2, 144 - scoreFont.getStringSize(score).height - 1, g);
	}
	
	@Override
	public EntityType getType(){
		
		return EntityType.PLAYER;
		
	}
}
