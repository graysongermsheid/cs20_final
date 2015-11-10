package gamescreen;

import java.awt.Graphics2D;
import java.awt.Color;
import gui.*;
import resources.*;

public class GameMenu extends Menu {

	public GameMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_bold.png");

	}

	@Override
	public void loadResources(){

		super.loadResources();
		this.components = new GUIComponent[3];

		components[0] = new LabeledButton("Quit!", 416, 416, 192){

			@Override
			public void performAction(){

				game.ScreenManager.switchCurrentScreen(new MainMenu(), false);

			}
		};
		components[1] = new LabeledButton("Runner", 113, 142, 192);
		components[2] = new LabeledButton("Minesweeper", 325, 142, 192);

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
	public void draw(Graphics2D g){

		font.setScaling(3);
		font.setColor(Color.RED);
		font.drawText(getTitle(), (640 / 2) - (font.getStringSize(getTitle()).width) / 2, 32, g);
		font.setScaling(1);
		super.draw(g);

	}

	@Override
	public String getTitle(){

		return "Select a Game:";

	}
}