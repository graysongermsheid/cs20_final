package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import game.ScreenManager;
import java.awt.Color;

public class MainMenu extends Menu {

	public MainMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_bold.png");

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[1];

		components[0] = new LabeledButton("~", 0, 0, 32){

			@Override
			public void performAction(){

				ScreenManager.switchCurrentScreen(new GameMenu(), true);

			}
		};

	}

	@Override
	public void draw(Graphics2D g){

		font.setScaling(3);
		font.setColor(Color.BLUE);
		font.setBackgroundColor(Color.YELLOW);
		font.drawShadowedText("AABBCCDDEEFF", (640 / 2) - (font.getStringSize(getTitle()).width) / 2, 32, g);
		font.setScaling(1);

		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "~~~";

	}
}