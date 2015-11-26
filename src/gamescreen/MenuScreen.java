package gamescreen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import gui.GUIComponent;
import input.InputHandler;
import resources.ResourceManager;
import resources.SpriteFont;

public abstract class MenuScreen implements GameScreen {

	protected GUIComponent[] components;
	protected SpriteFont font;

	@Override
	public void processInput(){

		if (components == null) {return;}

		for (int i = 0; i < components.length; i++){

			if (components[i].isVisible() && !isOverlapped(i)){

				components[i].processInput();

			}
		}
	}

	@Override
	public void update(double elapsedMilliseconds){

		if (components == null) {return;}

		for (int i = 0; i < components.length; i++){

			components[i].update(elapsedMilliseconds);

		}
	}

	@Override
	public void draw(Graphics2D g){

		font.setScaling(3.5f);
		font.setColor(new Color(0xDC, 0x3D, 0x24));
		font.setBackgroundColor(new Color(0xE3, 0xAE, 0x57));
		font.drawShadowedText(getTitle(), 320 - (font.getStringSize(getTitle()).width / 2), 32, g);
		font.setScaling(1);
		
		if (components == null) {return;}

		for (int i = 0; i < components.length; i++){

			if (components[i].isVisible()){

				components[i].draw(g);

			}
		}
	}

	@Override
	public String getTitle(){

		return "Default Menu Title";

	}

	@Override
	public void loadResources(){

		ResourceManager.createSpriteSheet("font.png", 16, 16);
		ResourceManager.createSpriteSheet("font_2.png", 7, 9);
		ResourceManager.createSpriteSheet("font_special.png", 9, 12);
		ResourceManager.createSpriteSheet("font_bold.png", 16, 16);
		ResourceManager.createSpriteSheet("font_large.png", 32, 32);

		ResourceManager.createSpriteSheet("button.png", 24, 24);
		ResourceManager.createSpriteSheet("composite_one.png", 16, 16);
		ResourceManager.createSpriteSheet("composite_two.png", 16, 16);
		ResourceManager.createSpriteSheet("composite_three.png", 16, 16);
		ResourceManager.createSpriteSheet("composite_four.png", 8, 8);
		ResourceManager.createSpriteSheet("textboxArrow.png", 11, 11);
		ResourceManager.createSpriteSheet("textboxProceed.png", 96, 26);

		ResourceManager.addSound("switch31.wav");

		this.font = ResourceManager.getFont("font_bold.png");

	}

	@Override
	public void unloadResources(){

		ResourceManager.clearResources();

	}

	protected boolean isOverlapped(int component){

		Point location2 = components[component].getLocation();

		for (int i = component + 1; i < components.length; i++){

			if ((components[component].isInsideBoundaries(InputHandler.MOUSE_LOCATION)) && 
				(components[i].isInsideBoundaries(InputHandler.MOUSE_LOCATION)) &&
				components[i].isVisible()){
				
				return true;

			}
		}

		return false;
	}
}