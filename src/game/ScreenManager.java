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
		
		displayFont = ResourceManager.getFont("font_bold.png");

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

			g.setColor(new Color(0x13, 0x1B, 0x1B));
			g.fillRect(0, 0, 640, 480);

			displayFont.setScaling(2.5f);
			displayFont.setColor(new Color(0xDC, 0x3D, 0x24));
			displayFont.setBackgroundColor(new Color(0xE3, 0xAE, 0x57));

			displayFont.drawShadowedText(screenTitle, (640 / 2) - (displayFont.getStringSize(screenTitle).width / 2), (480 / 2) - (displayFont.getStringSize(screenTitle).height / 2), g);

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