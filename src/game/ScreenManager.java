package game;

import gamescreen.*;
import resources.*;
import java.awt.Graphics2D;
import java.awt.Font;

public class ScreenManager {
	
	private static GameScreen currentScreen;
	private static Font displayFont;

	public ScreenManager(){

		currentScreen = new MainMenu();
		displayFont = new Font("Consolas", Font.PLAIN, 12);

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

	public static Font getFont(){

		return displayFont;

	}
}