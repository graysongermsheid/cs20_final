package gui;

import resources.*;
import input.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class SelectionBox extends GUIComponent {
	
	private ArrayList<GUIButton> buttons;
	private boolean buttonHeld;
	private double timer;
	private BufferedImage image;
	private BufferedImage selector;
	private int currentSelection;

	public SelectionBox(int x, int y, int width, int height){

		super("composite_two.png", x, y, 16, 16);
		image = ImageTools.combineImages(width, height, source.getAllImages());
		currentSelection = 0;
		timer = 0d;
		buttonHeld = false;
		selector = ResourceManager.getImage("buttonSelector.png");

	}

	public void show(){

		setVisible(true);

	}

	public void addButton(GUIButton button){

		buttons.add(button);

	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_LEFT_PRESSED && !buttonHeld){

			currentSelection = (currentSelection == 0) ? currentSelection : currentSelection - 1;
			buttonHeld = true;

		} else if (InputHandler.KEY_RIGHT_PRESSED && !buttonHeld){

			currentSelection = (currentSelection == buttons.size() - 1) ? currentSelection : currentSelection + 1;
			buttonHeld = true;

		} else if (!InputHandler.KEY_RIGHT_PRESSED && !InputHandler.KEY_LEFT_PRESSED){

			buttonHeld = false;

		}

		if (InputHandler.KEY_ACTION_PRESSED){

			buttons.get(currentSelection).performAction();
			setVisible(false);

		}
	}

	@Override
	public void update(double elapsedMilliseconds){

		if (buttonHeld){

			timer += elapsedMilliseconds;

			if (timer > 1000){

				timer = 0d;
				buttonHeld = false;

			}
		}

		for (GUIButton b : buttons){

			b.update(elapsedMilliseconds);

		}
	}

	@Override
	public void draw(Graphics2D g){

		g.drawImage(image, location.x, location.y, null);

		for (GUIButton button : buttons){

			button.draw(g);

		}

		g.drawImage(selector, buttons.get(currentSelection).location.x - 4, buttons.get(currentSelection).location.y - 4, null);

	}
}