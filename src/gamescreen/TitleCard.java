package gamescreen;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import resources.SpriteFont;
import resources.ResourceManager;
import input.InputHandler;

public class TitleCard implements GameScreen {
	
	private BufferedImage titleCard;
	private Point imageOffset;
	private Point textOffset;
	private double timer;
	private SpriteFont font;

	public TitleCard(){

		loadResources();

		titleCard = ResourceManager.getImage("title.png");
		font = ResourceManager.getFont("font_small.png");
		font.setScaling(3);
		timer = 0d;

		int imageOffsetX = (ScreenManager.screenSize.width / 2) - (titleCard.getWidth() / 2);
		int imageOffsetY = (ScreenManager.screenSize.height / 2) - (titleCard.getHeight() / 2);
		imageOffset = new Point(imageOffsetX, imageOffsetY);

		int textOffsetX = (ScreenManager.screenSize.width / 2) - (font.getStringSize("PRESS ANY BUTTON!").width / 2);
		int textOffsetY = (ScreenManager.screenSize.height) - (ScreenManager.screenSize.height / 5);
		textOffset = new Point(textOffsetX, textOffsetY);

	}

	public void update(double elapsedMilliseconds){

		timer += elapsedMilliseconds;

	}

	public void draw(Graphics2D g){

		g.drawImage(titleCard, imageOffset.x, imageOffset.y, null);

		if (timer > 2500 && (int)(timer / 750.0) % 2 == 0){

			font.drawText("PRESS ANY BUTTON!", textOffset.x, textOffset.y, g);

		}

	}

	public void processInput(){

		if ((timer > 3000) && (InputHandler.ANY_KEY_PRESSED)){

			ResourceManager.playSound("menu.wav");
			ScreenManager.switchCurrentScreen(new MainMenu());

		}

	}

	public String getTitle(){

		return null;

	}

	public void loadResources(){

		ResourceManager.createSpriteSheet("font_small.png", 4, 6);

	}

	public void unloadResources(){

		ResourceManager.clearResources();

	}

}