package gamescreen;

import gamescreen.*;
import resources.*;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;

public class ScreenManager {
	
	private static GameScreen currentScreen;
	private static SpriteFont displayFont;
	public static Dimension screenSize;

	public ScreenManager(int width, int height){

		screenSize = new Dimension(width, height);
		currentScreen = new TitleCard();

	}

	public void processInput(){

		currentScreen.processInput();

	}

	public void update(double elapsedMilliseconds){

		currentScreen.update(elapsedMilliseconds);

	}

	public void draw(Graphics2D g){

		currentScreen.draw(g);

	}

	public static void switchCurrentScreen(GameScreen newScreen){
		
		currentScreen.unloadResources();
		currentScreen = newScreen;

	}
}