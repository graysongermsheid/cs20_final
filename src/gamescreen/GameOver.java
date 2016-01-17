package gamescreen;

import java.awt.Graphics2D;

import gui.*;
import resources.SpriteFont;

public class GameOver extends MenuScreen {

	private int score;
	private int level;
	private String monsterName;
	
	public GameOver(int score, int level, String monsterName){
		
		super();
		
		font = resources.ResourceManager.getFont("font.png");
		
		this.score = score;
		this.level = level;
		this.monsterName = monsterName;
		
		components = new GUIComponent[2];
		
		components[0] = new Button("Exit Game", 0, 0, 256, 32);
		components[1] = new Button("Restart?", 300, 0, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new Game());
				
			}
			
		};
		
	}
	
	@Override
	public void draw(Graphics2D g){
		
		super.draw(g);
		
		font.setScaling(8f);
		font.setColor(java.awt.Color.WHITE);
		
		font.drawText("GAME OVER", 144, 96, g);
		
		font.setScaling(2.0f);
		font.setColor(java.awt.Color.YELLOW);
		
		font.drawText("SCORE: " + score, 208, 208, g);
		font.drawText("LEVEL REACHED: " + level, 208, 240, g);
		font.drawText("KILLED BY: " + monsterName, 208, 272, g);
		
		font.setScaling(1.0f);
		font.setColor(java.awt.Color.WHITE);
	}
	
	/*@Override
	public void loadResources(){
		
		super.loadResources();
		resources.ResourceManager.createSpriteSheet("font.png", 16, 16);
		resources.ResourceManager.createSpriteSheet("button.png", 24, 24);
		
	}*/
}
