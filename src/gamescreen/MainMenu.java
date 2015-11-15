package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import game.ScreenManager;
import java.awt.Color;
import java.awt.Polygon;

public class MainMenu extends MenuScreen {

	public MainMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_2.png");

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[4];

		components[0] = new Button("Play Game!", 320, 240);
		components[1] = new Button("Options", 320, 280);
		components[2] = new Button("High Scores", 320, 320);
		components[3] = new Button("Quit", 320, 360);

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