package gui;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import resources.Animation;
import resources.ResourceManager;
import resources.SpriteFont;
import resources.ImageTools;
import input.InputHandler;


public class Button extends GUIComponent {
	
	private Animation states;
	private ButtonState currentState;
	private String text;
	private SpriteFont font;
	private Point textOffset;

	public Button(String text, int x, int y, int width, int height){

		super("button.png", x, y, width, height);
		states = new Animation(source, 1.0);
		font = new SpriteFont(ResourceManager.getSpriteSheet("font_bold.png"));
		visible = true;
		this.text = text;
		currentState = ButtonState.DEFAULT;

		textOffset = new Point((width / 2) - (font.getStringSize(text).width / 2), (height / 2) - (font.getStringSize(text).height / 2));

		for (int i = 0; i < states.getAllImages().length; i++){

			states.setFrame(i, ImageTools.resizeImage(states.getFrame(i), width, height));

		}
	}

	@Override
	public void update(double elapsedMilliseconds){

		states.setCurrentFrame(currentState.value());

	}

	@Override
	public void processInput(){

		if (isClicked() && currentState	!= ButtonState.DOWN){

			currentState = ButtonState.DOWN;
			performAction();

		} else if (isInsideBoundaries(InputHandler.MOUSE_LOCATION) && !isClicked()){

			currentState = ButtonState.MOUSED_OVER;

		} else if (!isInsideBoundaries(InputHandler.MOUSE_LOCATION) && !isClicked()){

			currentState = ButtonState.DEFAULT;

		}
	}

	@Override
	public void draw(Graphics2D g){

		g.drawImage(states.getCurrentFrame(), location.x, location.y, null);

		switch(currentState){

			case DOWN:

				font.setColor(Color.WHITE);
				break;

			case MOUSED_OVER:

				font.setColor(Color.BLUE);
				break;

			case DEFAULT:
			default:

				font.setColor(Color.BLACK);
				break;
		}

		font.drawText(text, location.x + textOffset.x, location.y + textOffset.y, g);

	}

	public Point getLocation(){

		return location;

	}

	public void setLocation(int x, int y){

		location = new Point(x, y);

	}

	public void performAction(){}
}