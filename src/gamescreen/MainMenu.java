package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import java.awt.Color;

public class MainMenu extends Menu {

	public MainMenu(){

		super.loadResources();
		this.font = ResourceManager.getFont("font_bold.png");

		initialize();

	}

	private void initialize(){

		this.components = new GUIComponent[4];

		components[0] = new LabeledButton("     Play Game    ", 196, 100);
		components[1] = new LabeledButton("Options", 113, 142);
		components[2] = new LabeledButton("Quit", 325, 142);
		components[3] = new TextBox2("message.txt", 0, 0, 256, 256);

	}

	@Override
	public void draw(Graphics2D g){

		font.drawColoredText(getTitle(), (640 / 2) - (font.widthOfString(getTitle()) * 3) / 2, 32, Color.WHITE, 3, g);

		super.draw(g);

	}

	@Override
	public void update(double elapsedMilliseconds){

		super.update(elapsedMilliseconds);

	}

	@Override
	public void processInput(){

		super.processInput();
	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}