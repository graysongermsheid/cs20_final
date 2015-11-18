package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import java.awt.Color;

public class MainMenu extends MenuScreen {

	public MainMenu(){

		loadResources();

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[3];

<<<<<<< HEAD
		components[0] = new Button("~Click~", 256, 96, 128, 32){
=======
		components[0] = new Button("~Click~", 276, 96, 128, 32){
>>>>>>> ba89c99bbc895b3894da940b13b092a304bf7b28

			@Override
			public void performAction(){

				ScreenManager.switchCurrentScreen(new Game(), true);

			}
		};
<<<<<<< HEAD
		components[1] = new Button("TextBox", 256, 136, 128, 32){
=======
		components[1] = new Button("TextBox", 276, 136, 128, 32){
>>>>>>> ba89c99bbc895b3894da940b13b092a304bf7b28

			@Override
			public void performAction(){

				TextBox t = (TextBox) components[2];
				t.show();

			}
		};
		components[2] = new TextBox("message.txt", 192, 176, 256, 48);

	}

	@Override
	public void draw(Graphics2D g){

		//g.drawImage(ResourceManager.getImage("title.png"), 0, 0, null);
		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}