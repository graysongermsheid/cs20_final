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
		
		displayFont = ResourceManager.getFont("font_special.png");

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

			displayFont.setScaling(4);
			displayFont.setColor(Color.WHITE);

			displayFont.drawText(screenTitle, (640 / 2) - (displayFont.getStringSize(screenTitle).width / 2), (480 / 2) - (displayFont.getStringSize(screenTitle).height / 2), g);

			displayFont.setScaling(1);

		} else {

			currentScreen.draw(g);

		}

	}

	public static void switchCurrentScreen(GameScreen newScreen, boolean doTransition){


		
		currentScreen.unloadResources();
		currentScreen = newScreen;
		screenTitle = newScreen.getTitle();
		transition = doTransition;
		newScreen = null;

	}
}