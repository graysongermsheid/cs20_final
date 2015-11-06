package game;

import gamescreen.*;
import resources.*;
import java.awt.Graphics2D;
import java.awt.Color;

public class ScreenManager {
	
	private static GameScreen currentScreen;
	private static GameScreen nextScreen;
	private static SpriteFont displayFont;

	private static double timer;
	private static boolean transition;
	private static String screenTitle;

	public ScreenManager(){

		timer = 0d;
		transition = false;


		currentScreen = new MainMenu();
		
		displayFont = ResourceManager.getFont("font.png");

	}

	public void processInput(){

		if (transition){return;}

		currentScreen.processInput();

	}

	public void update(double elapsedMilliseconds){

		if (transition){

			timer += elapsedMilliseconds;

			if (timer >= 3000d){

				transition = false;
				timer = 0d;
			}

		} else {

			currentScreen.update(elapsedMilliseconds);

		}
	}

	public void draw(Graphics2D g){

		if (transition){

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 640, 480);

			displayFont.drawColoredText("*", ((640 / 2) - ((displayFont.widthOfString("*") * 4) / 2)), ((480 / 2) - (displayFont.getOriginalSize().height / 2) - 32), Color.WHITE, 4, g);

		} else {

			currentScreen.draw(g);

		}

	}

	public static void switchCurrentScreen(GameScreen newScreen){


		
		currentScreen.unloadResources();
		currentScreen = newScreen;
		screenTitle = newScreen.getTitle();
		transition = true;
		newScreen = null;

	}
}