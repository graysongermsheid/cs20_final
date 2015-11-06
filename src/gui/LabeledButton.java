package gui;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import resources.*;

public class LabeledButton extends GUIButton {
	
	private String text;
	private SpriteFont spritefont;
	private Point textOffset;

	public LabeledButton(String text, int x, int y){

		super("button.png", x, y, 192, 32);
		this.spritefont = ResourceManager.getFont("font.png");
		this.text = text;

		if (spritefont.widthOfString(text) > size.width - 8){

			int largeWidth = spritefont.widthOfString(text) + 8;

			image.changeFrameImage(0, CompositeImage.resizeImage(image.getFrame(0), largeWidth, size.height));
			image.changeFrameImage(1, CompositeImage.resizeImage(image.getFrame(1), largeWidth, size.height));
			image.changeFrameImage(2, CompositeImage.resizeImage(image.getFrame(2), largeWidth, size.height));

		}

		size = new Dimension(image.getSize().width, size.height);
		
		int offsetX = location.x + (size.width / 2) - (spritefont.widthOfString(text) / 2);
		int offsetY = location.y + (size.height / 2) - (spritefont.getSize().height / 2);

		textOffset = new Point(offsetX, offsetY);

		currentState = ButtonState.DEFAULT;

		System.out.println("Button: " + text + " size = " + size.width + "x" + size.height);
	}

	public LabeledButton(String font, String text, int x, int y){

		super("button.png", x, y, 192, 32);

		this.text = text;
		this.spritefont = ResourceManager.getFont(font);

		if (spritefont.widthOfString(text) > size.width - 8){

			int largeWidth = spritefont.widthOfString(text) + 8;

			image.changeFrameImage(0, CompositeImage.resizeImage(image.getFrame(0), largeWidth, size.height));
			image.changeFrameImage(1, CompositeImage.resizeImage(image.getFrame(1), largeWidth, size.height));
			image.changeFrameImage(2, CompositeImage.resizeImage(image.getFrame(2), largeWidth, size.height));

		}

		size = new Dimension(image.getSize().width, size.height);
		
		int offsetX = location.x + (size.width / 2) - (spritefont.widthOfString(text) / 2);
		int offsetY = location.y + (size.height / 2) - (spritefont.getSize().height / 2);

		textOffset = new Point(offsetX, offsetY);

		currentState = ButtonState.DEFAULT;
	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

		if (currentState == ButtonState.DOWN){

			spritefont.drawColoredText(text, textOffset.x, textOffset.y, Color.WHITE, g);

		} else if (currentState == ButtonState.MOUSED_OVER){

			spritefont.drawColoredText(text, textOffset.x, textOffset.y, Color.BLUE, g);

		} else {

			spritefont.drawColoredText(text, textOffset.x, textOffset.y, Color.BLACK, g);

		}
	}
}