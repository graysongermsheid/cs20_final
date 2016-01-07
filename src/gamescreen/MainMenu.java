package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import java.awt.Color;
import java.awt.Polygon;

public class MainMenu extends MenuScreen {

	public MainMenu(){

		

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[3];

		components[0] = new Button("~Click~", 256, 96, 128, 32){

			@Override
			public void performAction(){

				ScreenManager.switchCurrentScreen(new Game());

			}
		};
		components[1] = new Button("TextBox", 256, 136, 128, 32){

			@Override
			public void performAction(){

				((TextBox) components[2]).show();

			}
		};
		components[2] = new TextBox("message.txt", 192, 176, 640, 480);

	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}