package game;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import input.InputHandler;
import level.Level;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import resources.ResourceManager;
import resources.SpriteFont;

public class Camera {

	private Point location; //Pixels
	private Point farLocation; //far corner
	private Dimension size; //Pixels
	private Focus focus;
	private SpriteFont f;
	
	private Level currentLevel;
	
	public Camera(int x, int y, int width, int height, Level l){
		
		location = new Point(x, y);
		f = ResourceManager.getFont("font_bold.png");
		width += 16;
		height += 16;
		farLocation = new Point(x + width, y + height);
		size = new Dimension(width, height);
		currentLevel = l;
		
	}
	
	public void update(double elapsedMilliseconds){
		
		farLocation = new Point(location.x + size.width, location.y + size.height);
		
	}
	
	public void processInput(){
		
		if (InputHandler.KEY_LEFT_PRESSED){
			
			location.setLocation(location.x - 1, location.y);
			
		}
		
		if (InputHandler.KEY_RIGHT_PRESSED){
			
			location.setLocation(location.x + 1, location.y);
			
		}
		
		if (InputHandler.KEY_UP_PRESSED){
			
			location.setLocation(location.x, location.y - 1);
			
		}
		
		if (InputHandler.KEY_DOWN_PRESSED){
			
			location.setLocation(location.x, location.y + 1);
			
		}
	}
	
	public void draw(Graphics2D g){
		
		currentLevel.draw(location, farLocation, g);
		f.setColor(java.awt.Color.WHITE);
		f.drawText(location.x + ", " + location.y, 0, 0, g);
	}
	
	protected enum Focus {
		
		//Whether the camera follows the player, locks to the center or locks to a corner
	
		PLAYER,
		CENTER,
		CORNER;
		
	}
}
