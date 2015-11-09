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

	public LabeledButton(String text, int x, int y, int width){

		super("button.png", x, y, width);
		this.spritefont = ResourceManager.getFont("font.png");
		this.text = text;
		
		int offsetX = location.x + (size.width / 2) - (spritefont.getStringSize(text).width / 2);
		int offsetY = location.y + (size.height / 2) - (spritefont.getSize().height / 2);

		textOffset = new Point(offsetX, offsetY);

		currentState = ButtonState.DEFAULT;
	}

	public LabeledButton(String font, String text, int x, int y, int width){

		super("button.png", x, y, width);

		this.text = text;
		this.spritefont = ResourceManager.getFont(font);
		
		int offsetX = location.x + (size.width / 2) - (spritefont.getStringSize(text).width / 2);
		int offsetY = location.y + (size.height / 2) - (spritefont.getSize().height / 2);

		textOffset = new Point(offsetX, offsetY);

		currentState = ButtonState.DEFAULT;
	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

		if (currentState == ButtonState.DOWN){

			spritefont.setColor(Color.WHITE);
			spritefont.drawText(text, textOffset.x, textOffset.y, g);

		} else if (currentState == ButtonState.MOUSED_OVER){

			spritefont.setColor(Color.BLUE);
			spritefont.drawText(text, textOffset.x, textOffset.y, g);

		} else {

			spritefont.setColor(Color.BLACK);
			spritefont.drawText(text, textOffset.x, textOffset.y, g);

		}
	}
}