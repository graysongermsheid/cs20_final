package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import java.awt.Color;

public class MainMenu extends Menu {

	public MainMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_bold.png");

	}

	@Override
	public void loadResources(){

		super.loadResources();
		this.components = new GUIComponent[4];

		components[0] = new LabeledButton("     Play Game    ", 196, 100, 300){

			@Override
			public void performAction(){

				game.ScreenManager.switchCurrentScreen(new GameMenu(), false);

			}

		};
		components[1] = new LabeledButton("Options", 113, 142, 192);
		components[2] = new LabeledButton("Quit", 325, 142, 192){

			@Override
			public void performAction(){

				TextBox c = (TextBox) components[3];
				c.show();

			}

		};
		components[3] = new TextBox("message.txt", 0, 0, 256, 48);

	}

	@Override
	public void draw(Graphics2D g){

		font.setScaling(4);
		font.setColor(Color.WHITE);
		font.drawShadowedText(getTitle(), (640 / 2) - (font.getStringSize(getTitle()).width) / 2, 32, g);
		font.setScaling(1);

		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}