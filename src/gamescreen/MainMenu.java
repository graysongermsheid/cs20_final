package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
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
		components[1] = new Button("POPUP!", 32, 42, 96, 32){

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

		g.drawImage(ResourceManager.getImage("title.png"), 0, 0, null);
		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}