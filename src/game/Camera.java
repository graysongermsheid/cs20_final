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
	private BufferedImage drawImage;
	private AffineTransformOp transformer;
	private AffineTransform transform;
	
	public Level currentLevel;
	
	public Camera(int x, int y, int width, int height, Level l){
		
		location = new Point(x, y);
		transform = new AffineTransform();

		Dimension screenSize = gamescreen.ScreenManager.screenSize;
		float scaleVert = screenSize.height / (float)height;
		float scaleHoriz = screenSize.width / (float)width;

		transform.scale(scaleHoriz, scaleVert);
		transformer = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

		drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		f = ResourceManager.getFont("font_bold.png");
		farLocation = new Point(x + width, y + height);
		size = new Dimension(width, height);
		currentLevel = l;
		
	}

	public void setLevel(int x, int y, Level l){

		location = new Point(x, y);
		transform = new AffineTransform();
		farLocation = new Point(x + size.width, y + size.height);
		currentLevel = l;

	}
	
	public void setSize(int width, int height){
		
		size = new Dimension(width, height);
		farLocation = new Point(location.x + width, location.y + height);
		drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		transform = new AffineTransform();

		Dimension screenSize = gamescreen.ScreenManager.screenSize;
		float scaleVert = screenSize.height / (float)height;
		float scaleHoriz = screenSize.width / (float)width;

		transform.scale(scaleHoriz, scaleVert);
		transformer = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour
		
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
		
		location.x += (InputHandler.KEY_RIGHT_PRESSED && farLocation.x + 1 < currentLevel.getRealSize().width) ? 1 : 0;
		location.x -= (InputHandler.KEY_LEFT_PRESSED && location.x > 0) ? 1 : 0;

		location.y += (InputHandler.KEY_DOWN_PRESSED && farLocation.y + 1 < currentLevel.getRealSize().height) ? 1 : 0;
		location.y -= (InputHandler.KEY_UP_PRESSED && location.y > 0) ? 1 : 0;
			
	}
	
	public void draw(Graphics2D g){

		Graphics2D g2 = drawImage.createGraphics();
		g2.setColor(java.awt.Color.BLACK);
		//g2.fillRect(0, 0, drawImage.getWidth(), drawImage.getHeight());

		float scaleV = gamescreen.ScreenManager.screenSize.height / (float)size.height;
		float scaleH = gamescreen.ScreenManager.screenSize.width / (float)size.width;
		BufferedImage scaledImage = new BufferedImage((int)(size.width * scaleH), (int)(size.height * scaleV), BufferedImage.TYPE_INT_ARGB);
		currentLevel.draw(location, farLocation, g2);

		g2.dispose();

		//Scale the original image to fit the screen
		scaledImage = transformer.filter(drawImage, scaledImage);
		g.drawImage(scaledImage, 0, 0, null);

		//Draw camera information to the screen
		f.setColor(java.awt.Color.GREEN);
		f.setBackgroundColor(java.awt.Color.BLACK);
		f.drawShadowedText("[" + location.x + "," + location.y + "]" + " [" + farLocation.x + "," + farLocation.y + "]", 0, 0, g);
		f.drawShadowedText(currentLevel.getRealSize().width + " x " + currentLevel.getRealSize().height, 0, 18, g);
	}
	
	protected enum Focus {
		
		//Whether the camera follows the player, locks to the center or locks to a corner
	
		PLAYER,
		CENTER,
		CORNERS;
		
	}
}
