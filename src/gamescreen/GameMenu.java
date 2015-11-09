package gamescreen;

import java.awt.Graphics2D;
import java.awt.Color;
import gui.*;
import resources.*;

public class GameMenu extends Menu {
	
	private game.entities.Player p;

	public GameMenu(){

		loadResources();
		this.font = ResourceManager.getFont("font_bold.png");
		p = new game.entities.Player(0, 0);

	}

	@Override
	public void loadResources(){

		super.loadResources();

		ResourceManager.createSpriteSheet("Player.png", 32, 32);

		this.components = new GUIComponent[4];

		components[0] = new LabeledButton("Quit!", 416, 416, 192){

			@Override
			public void performAction(){

				game.ScreenManager.switchCurrentScreen(new MainMenu(), false);

			}
		};
		components[1] = new LabeledButton("Runner", 113, 142, 192){

			@Override
			public void performAction(){

				SelectionBox sb = (SelectionBox) components[3];
				sb.show();

			}
		};
		components[2] = new LabeledButton("Minesweeper", 325, 142, 192){

			@Override
			public void performAction(){

				System.out.println("Launch a minesweeper clone");

			}
		};
		components[3] = new SelectionBox(124, 368, 392, 48);

		SelectionBox sb = (SelectionBox) components[3];

		sb.addButton(new LabeledButton("Yea", 128, 372, 96){

			@Override
			public void performAction(){

				System.out.println("YEA");

			}

		});
		sb.addButton(new LabeledButton("Nah", 328, 372, 96));

	}

	@Override
	public void update(double elapsedMilliseconds){

		super.update(elapsedMilliseconds);
		p.update(elapsedMilliseconds);

	}

	@Override
	public void processInput(){

		super.processInput();
		p.processInput();

	}

	@Override
	public void draw(Graphics2D g){

		font.setScaling(3);
		font.setColor(Color.RED);
		font.drawText(getTitle(), (640 / 2) - (font.getStringSize(getTitle()).width) / 2, 32, g);
		font.setScaling(1);
		super.draw(g);
		p.draw(g);

	}

	@Override
	public String getTitle(){

		return "Select a Game:";

	}
}