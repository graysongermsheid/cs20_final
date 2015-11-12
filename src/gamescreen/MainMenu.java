package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import game.ScreenManager;
import java.awt.Color;

public class MainMenu extends Menu {

	public MainMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_special.png");

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[3];

		components[0] = new Button("Special Font", 0, 0, 128, 32){

			@Override
			public void performAction(){

				ScreenManager.switchCurrentScreen(new GameMenu(), true);

			}
		};
		components[1] = new Button("POPUP!", 0, 42, 128, 32){

			@Override
			public void performAction(){

				components[2].setVisible(!components[2].isVisible());

			}
		};
		components[2] = new GUIMenu("composite_four.png", 128, 128, 128, 128);

		GUIMenu m = (GUIMenu) components[2];
		m.addButton(new Button("~", 32, 32, 32, 32));

	}

	@Override
	public void draw(Graphics2D g){

		font.setScaling(3);
		font.setColor(Color.BLACK);
		font.setBackgroundColor(Color.WHITE);
		font.drawShadowedText(getTitle(), (640 / 2) - (font.getStringSize(getTitle()).width) / 2, 32, g);
		font.setScaling(1);

		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}