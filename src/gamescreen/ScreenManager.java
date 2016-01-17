package gamescreen;

import java.awt.Graphics2D;
import java.awt.Dimension;

public class ScreenManager {
	
	private static GameScreen currentScreen;
	public static Dimension screenSize;

	public ScreenManager(int width, int height){

		screenSize = new Dimension(width, height);
		currentScreen = new Game();
		currentScreen.loadResources();

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
		currentScreen.loadResources();

	}
}