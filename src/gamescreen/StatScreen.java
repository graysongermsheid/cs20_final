package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;

import game.ScoreTracker;
import gui.*;

public class StatScreen extends MenuScreen {

	public StatScreen(){
		
		components = new GUIComponent[3];
		
		components[0] = new Button("Exit Game", 512, 512, 256, 32){
			
			@Override
			public void performAction(){
				
				System.exit(0);
				
			}
			
		};
		
		components[1] = new Button("Restart", 512, 556, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new Game());
				
			}
			
		};
		
		components[2] = new Button("Main Menu", 512, 468, 256, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new MainMenu());
				
			}
			
		};
	}

	@Override
	public void draw(Graphics2D g){
		
		super.draw(g);
		
		font.setScaling(2f);
		font.setColor(Color.WHITE);
		
		int baseY = 128;
		int baseX = 448;
		
		font.drawText("Score: " + ScoreTracker.score, baseX, baseY, g);
		font.drawText("Gold Collected: $" + ScoreTracker.goldCollected, baseX, baseY + 36, g);
		font.drawText("Health Lost: " + ScoreTracker.healthLost, baseX, baseY + 72, g);
		font.drawText("Health Healed: " + ScoreTracker.healthHealed, baseX, baseY + 108, g);
		font.drawText("Areas Explored: " + ScoreTracker.areasExplored, baseX, baseY + 144, g);
		font.drawText("Killed By: " + ScoreTracker.killedBy, baseX, baseY + 180, g);
	}
}
