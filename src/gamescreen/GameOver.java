package gamescreen;

import java.awt.Graphics2D;

import game.ScoreTracker;
import gui.*;
import resources.SpriteFont;

public class GameOver extends MenuScreen {

	private String monsterName;
	
	public GameOver(String monsterName){
		
		super();
		ScoreTracker.killedBy = monsterName;
		
		components = new GUIComponent[4];
		
		components[0] = new Button("Exit Game", 119, 668, 256, 32){
			
			@Override
			public void performAction(){
				
				System.exit(0);
				
			}
			
		};
		
		components[1] = new Button("Restart?", 387, 668, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new Game());
				
			}
			
		};
		
		components[2] = new Button("Stats", 655, 668, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new StatScreen());
				
			}
			
		};
		
		components[3] = new Button("Main Menu", 923, 668, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new MainMenu());
				
			}
			
		};
	}
	
	@Override
	public void draw(Graphics2D g){
		
		super.draw(g);
		
		font.setScaling(8f);
		font.setColor(java.awt.Color.WHITE);
		
		font.drawText("GAME OVER", 144, 96, g);
		
		font.setScaling(1f);
	}
	
	/*@Override
	public void loadResources(){
		
		super.loadResources();
		resources.ResourceManager.createSpriteSheet("font.png", 16, 16);
		resources.ResourceManager.createSpriteSheet("button.png", 24, 24);
		
	}*/
}
