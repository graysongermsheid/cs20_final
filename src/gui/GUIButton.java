package gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import resources.Animation;
import resources.CompositeImage;
import resources.ImageTools;
import input.InputHandler;

public class GUIButton extends GUIComponent{
	
	protected Animation image;
	protected ButtonState currentState;

	public GUIButton(String spriteSheet, int x, int y, int width){

		super(spriteSheet, x, y, 192, 32);
		this.image = new Animation(source, 1.0f);

		if (width != 192){

			//changed to imagetools
			image.changeFrameImage(0, ImageTools.resizeImage(image.getFrame(0), width, size.height));
			image.changeFrameImage(1, ImageTools.resizeImage(image.getFrame(1), width, size.height));
			image.changeFrameImage(2, ImageTools.resizeImage(image.getFrame(2), width, size.height));

			size = new Dimension(width, size.height);
		}

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