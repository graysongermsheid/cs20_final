package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import game.ScreenManager;
import java.awt.Color;

public class MainMenu extends MenuScreen {

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
		components[1] = new Button("POPUP!", 0, 42, 128, 128){

			@Override
			public void performAction(){

				TextBox t = (TextBox) components[2];
				t.show();

			}
		};
		components[2] = new TextBox("message.txt", 128, 128, 128, 128);

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