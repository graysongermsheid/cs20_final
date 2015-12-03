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
	private BufferedImage image;
	private AffineTransformOp transformer;
	private AffineTransform transform;
	
	private Level currentLevel;
	
	public Camera(int x, int y, int width, int height, Level l){
		
		location = new Point(x, y);
		transform = new AffineTransform();
		transform.scale(gamescreen.ScreenManager.screenSize.height / height, gamescreen.ScreenManager.screenSize.height / height);
		transformer = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		f = ResourceManager.getFont("font_bold.png");
		width += 16;
		height += 16;
		farLocation = new Point(x + width, y + height);
		size = new Dimension(width, height);
		currentLevel = l;
		
	}

	public void setLevel(int x, int y, int width, int height, Level l){

		location = new Point(x, y);
		transform = new AffineTransform();
		transform.scale(gamescreen.ScreenManager.screenSize.height / height, gamescreen.ScreenManager.screenSize.height / height);
		transformer = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		f = ResourceManager.getFont("font_bold.png");
		width += 16;
		height += 16;
		farLocation = new Point(x + width, y + height);
		size = new Dimension(width, height);
		currentLevel = l;
		
	}
	
	public void update(double elapsedMilliseconds){
		
		farLocation = new Point(location.x + size.width, location.y + size.height);

		if (farLocation.x > currentLevel.getRealSize().width) {

			location.x = currentLevel.getRealSize().width - size.width;
			farLocation = new Point(location.x + size.width, location.y + size.height);

		}
		
		if (farLocation.y > currentLevel.getRealSize().height){

			location.y = currentLevel.getRealSize().height - size.height;
			farLocation = new Point(location.x + size.width, location.y + size.height);

		}
	}
	
	public void processInput(){
		
		if (InputHandler.KEY_LEFT_PRESSED){
			
			location.x -= (location.x == 0) ? 0 : 1;
			
		}
		
		if (InputHandler.KEY_RIGHT_PRESSED){
			
			location.x += (farLocation.x + 1 > currentLevel.getRealSize().width) ? 0 : 1;
			
		}
		
		if (InputHandler.KEY_UP_PRESSED){
			
			location.y -= (location.y == 0) ? 0 : 1;
			
		}
		
		if (InputHandler.KEY_DOWN_PRESSED){
			
			location.y += (farLocation.y + 1 >= currentLevel.getRealSize().height) ? 0 : 1;
			
		}
	}
	
	public void draw(Graphics2D g){

		Graphics2D g2 = image.createGraphics();
		int scale = gamescreen.ScreenManager.screenSize.height / size.height;
		BufferedImage i2 = new BufferedImage(size.height * scale, size.width * scale, BufferedImage.TYPE_INT_ARGB);
		currentLevel.draw(location, farLocation, g2);
		g2.dispose();
		i2 = transformer.filter(image, i2);
		g.drawImage(i2, 0, 0, null);
		f.setColor(java.awt.Color.WHITE);
		f.drawText("[" + location.x + "," + location.y + "]" + " [" + farLocation.x + "," + farLocation.y + "]", 0, 0, g);
		f.drawText(currentLevel.getRealSize().width + " x " + currentLevel.getRealSize().height, 0, 18, g);
	}
	
	protected enum Focus {
		
		//Whether the camera follows the player, locks to the center or locks to a corner
	
		PLAYER,
		CENTER,
		CORNER;
		
	}
}
