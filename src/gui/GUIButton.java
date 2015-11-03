package gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import resources.Animation;
import input.InputHandler;

public class GUIButton extends GUIComponent{
	
	protected Animation image;
	protected ButtonState currentState;

	public GUIButton(String spriteSheet, int x, int y, int width, int height){

		super(spriteSheet, x, y, width, height);
		this.image = resources.ResourceManager.animationFromSheet(source, 1.0f);

		visible = true;
		currentState = ButtonState.DEFAULT;
	}

	@Override
	public void draw(Graphics2D g){
	
		g.drawImage(image.currentFrame(), location.x, location.y, null);

	}

	@Override
	public void processInput(){

		if (isInsideBoundaries(InputHandler.MOUSE_LOCATION)){

			if (InputHandler.MOUSE_CLICKED && currentState != ButtonState.DOWN){

				currentState = ButtonState.DOWN;
				performAction();

			} else if (!InputHandler.MOUSE_CLICKED){

				currentState = ButtonState.MOUSED_OVER;

			}

		}

		if (!InputHandler.MOUSE_CLICKED && !isInsideBoundaries(InputHandler.MOUSE_LOCATION)){

			currentState = ButtonState.DEFAULT;

		} 
	}

	@Override
	public void update(double elapsedMilliseconds){

		image.setFrame(currentState.value());

	}

	public void performAction(){


	}
}