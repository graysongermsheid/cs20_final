package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.CompositeImage;

public class MainMenu extends Menu {

	public MainMenu(){

		super.loadResources();
		initialize();

	}

	private void initialize(){

		this.components = new GUIComponent[5];

		components[0] = new LabeledButton("Click Here", 100, 100){

			@Override
			public void performAction(){

				components[2].setVisible(!components[2].isVisible());
				components[3].setVisible(!components[3].isVisible());

			}

		};
		components[1] = new LabeledButton("Start Game", 307, 100){

			@Override
			public void performAction(){

				TextBox2 t = (TextBox2) components[4];

				t.show();

			}

		};

		components[2] = new GUIMenu("composite_three.png", 0, 0, 240, 240, "Popup Menu");
		components[3] = new GUIMenu("composite_one.png", 240, 240, 240, 240);
		components[4] = new TextBox2("butts.txt", 0, 0, 256, 128);

	}

	@Override
	public void draw(Graphics2D g){

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
}