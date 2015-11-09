package gui;

import input.InputHandler;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;

public class DynamicButton extends GUIButton {

	private Point locationOffset;

	public DynamicButton(String imageName, int x, int y, int width){

		super(imageName, x, y, width);

	}

	public void processInput(Point locationOffset){

		Point correctedPoint = new Point(InputHandler.MOUSE_LOCATION.x - locationOffset.x, InputHandler.MOUSE_LOCATION.y - locationOffset.y);

		if (isInsideBoundaries(correctedPoint)){

			if (InputHandler.MOUSE_CLICKED && currentState != ButtonState.DOWN){

				currentState = ButtonState.DOWN;
				performAction();
				System.out.println("Perform an action");

			} else if (!InputHandler.MOUSE_CLICKED){

				currentState = ButtonState.MOUSED_OVER;

			}

		}

		if (InputHandler.MOUSE_CLICKED && isInsideBoundaries(correctedPoint) && currentState != ButtonState.DOWN){

			currentState = ButtonState.DOWN;
			performAction();
			System.out.println("Perform an action");

		}

		if (!InputHandler.MOUSE_CLICKED && !isInsideBoundaries(correctedPoint)){

			currentState = ButtonState.DEFAULT;

		} 

	}

	public void draw(Graphics2D g, Point locationOffset){

		Point correctedPoint = new Point(locationOffset.x + location.x, locationOffset.y + location.y);

		g.drawImage(image.currentFrame(), correctedPoint.x, correctedPoint.y, null);

	}
}