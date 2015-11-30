package gui;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import resources.Animation;
import resources.ResourceManager;
import resources.ImageTools;
import input.InputHandler;


public class Button extends GUIComponent {
	
	private Animation states;
	private ButtonState currentState;
	private String text;
	private FontHelper font;
	private Point textOffset;

	public Button(String text, int x, int y, int width, int height){

		super("button.png", x, y, width, height);
		states = new Animation(source, 1.0);
		font = new FontHelper("font_times", 12);
		visible = true;
		this.text = text;
		currentState = ButtonState.DEFAULT;

		textOffset = new Point((width  / 2) - (font.getStringSize(text).width / 2), (height / 2) - (font.getStringSize(text).height / 2));

		for (int i = 0; i < states.getAllImages().length; i++){

			states.setFrame(i, ImageTools.resizeImage(states.getFrame(i), width, height));

		}
	}

	public Button(String text, int x, int y){

		super ("button.png", x, y, 0, 0);
		states = new Animation(source, 1.0);
		font = new FontHelper("font_times", 12);
		visible = true;
		this.text = text;
		currentState = ButtonState.DEFAULT;

		for (int i = 0; i < states.getAllImages().length; i++){

			states.setFrame(i, ImageTools.resizeImage(states.getFrame(i), font.getStringSize(text).width + 10, 32));

		}
		size = new Dimension(font.getStringSize(text).width + 10, 32);
		textOffset = new Point((size.width / 2) - (font.getStringSize(text).width / 2), (size.height / 2) - (font.getStringSize(text).height / 2));

	}

	@Override
	public void update(double elapsedMilliseconds){

		states.setCurrentFrame(currentState.value());

	}

	@Override
	public void processInput(){

		if (isClicked() && currentState	!= ButtonState.DOWN){

			currentState = ButtonState.DOWN;
			ResourceManager.playSound("button.wav");
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
		int additionalOffset = 0;

		font.setColor(Color.WHITE);

		font.drawText(text, location.x + textOffset.x, location.y + textOffset.y + additionalOffset, g);

	}

	public Point getLocation(){

		return location;

	}

	public void setLocation(int x, int y){

		location = new Point(x, y);

	}

	public void performAction(){}

	public enum ButtonState {

		DOWN (0),
		DEFAULT (1),
		MOUSED_OVER (2);

		int stateValue;

		ButtonState(int stateValue){

			this.stateValue = stateValue;

		}

		public int value(){

			return stateValue;

		}
	}
}