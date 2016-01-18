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
import window.Screen;

public class Camera {

	private Point location; //Pixels
	private Point farLocation; //far corner
	private Dimension size; //Pixels
	private SpriteFont font;
	private BufferedImage drawImage;
	private AffineTransformOp transformer;
	private AffineTransform transform;
	private Player player;
	private EntityManager e;
	public static boolean debug; //whether or not to show game info
	private boolean debugHeld;
	
	public Level currentLevel;
	
	public Camera(int x, int y, int width, int height, Level l){
		
		location = new Point(x, y);
		transform = new AffineTransform();
		e = new EntityManager();
		
		Dimension screenSize = gamescreen.ScreenManager.screenSize;
		float scaleVert = screenSize.height / (float)height;
		float scaleHoriz = screenSize.width / (float)width;

		transform.scale(scaleHoriz, scaleVert);
		transformer = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

		drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		font = ResourceManager.getFont("font.png");
		farLocation = new Point(x + width, y + height);
		size = new Dimension(width, height);
		currentLevel = l;
		
		LivingEntity.setLevelCollisionLayer(l.getCollisionLayer());
		e.setReferenceCollisions(l.getCollisionLayer());
		player = (Player) e.spawnRandomLocation(EntityType.PLAYER);
		e.spawnRandomLocation(EntityType.MONSTER);
		
	}

	public void setLevel(int x, int y, Level l){

		location = new Point(x, y);
		transform = new AffineTransform();
		farLocation = new Point(x + size.width, y + size.height);
		currentLevel = l;
		LivingEntity.setLevelCollisionLayer(l.getCollisionLayer());

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
		
		e.update(elapsedMilliseconds);
		location = new Point(player.getCenter().x - size.width / 2, player.getCenter().y - size.height / 2);
		farLocation = new Point(location.x + size.width, location.y + size.height);

		if (farLocation.x > currentLevel.getRealSize().width) {

			location.x = currentLevel.getRealSize().width - size.width;
			farLocation = new Point(location.x + size.width, location.y + size.height);

		}
		
		if (farLocation.y > currentLevel.getRealSize().height){

			location.y = currentLevel.getRealSize().height - size.height;
			farLocation = new Point(location.x + size.width, location.y + size.height);

		}
		
		if (location.x < 0){
			
			location.x = 0;
			farLocation.x = location.x + size.width;
			
		}
		
		if (location.y < 0){
			
			location.y = 0;
			farLocation.y = location.y + size.height;
			
		}
	}
	
	public void processInput(){
		
		player.processInput();
		if (InputHandler.KEY_DEBUG_PRESSED && !debugHeld){
			
			debug = !debug;
			debugHeld = true;
			
		} else if (!InputHandler.KEY_DEBUG_PRESSED){
			
			debugHeld = false;
			
		}
		
	}
	
	public void draw(Graphics2D g){

		Graphics2D g2 = drawImage.createGraphics();
		g2.setColor(java.awt.Color.BLACK);
		//g2.fillRect(0, 0, drawImage.getWidth(), drawImage.getHeight());

		float scaleV = gamescreen.ScreenManager.screenSize.height / (float)size.height;
		float scaleH = gamescreen.ScreenManager.screenSize.width / (float)size.width;
		BufferedImage scaledImage = new BufferedImage((int)(size.width * scaleH), (int)(size.height * scaleV), BufferedImage.TYPE_INT_ARGB);
		currentLevel.draw(location, farLocation, g2);
		e.draw(g2, location);

		g2.dispose();

		//Scale the original image to fit the screen
		scaledImage = transformer.filter(drawImage, scaledImage);
		g.drawImage(scaledImage, 0, 0, null);

		//Draw camera information to the screen
		if (debug){

			drawDetails(g, scaleH, scaleV);
			
		}
	}
	
	private void drawDetails(Graphics2D g, float scaleH, float scaleV){
		
		g.setColor(new java.awt.Color(128, 128, 128, 128));
		g.fillRect(0, 0, 512, 36);
		
		int sH = (int) scaleH;
		int sV = (int) scaleV; //cause java doesnt let you use floats in rectangles
		
		font.setColor(java.awt.Color.GREEN);
		
		//Display camera bounds in the far and near corners
		String cameraNear = "[" + location.x + "," + location.y + "]";
		String cameraFar = "[" + farLocation.x + "," + farLocation.y + "]";
		font.drawText(cameraNear, 0, 0, g);
		font.drawText(cameraFar, Screen.SIZE.width - font.getStringSize(cameraFar).width, Screen.SIZE.height - font.getStringSize(cameraFar).height, g);
		font.drawText("fps: " + Screen.getFPS() + " (updates: " + Screen.getTicks() + ")", 0, 18, g);
		
		for (Entity p : e.getEntities()){
			
			g.setColor(java.awt.Color.GREEN);
			
			String info = p.getType().getInfo() + " | Location: " + "[" + p.getCenter().x + ", " + p.getCenter().getY() + "]";
			String info2 = (p.getType() != EntityType.ITEM) ? "HP: " + ((LivingEntity) p).getHealth() + " | " + (((LivingEntity)p).getAlive() ? "alive" : "dead") : " ";
			g.drawString(info, p.getHitBox().getX() * scaleH - location.x * scaleH, p.getHitBox().getFarLocation().y * scaleV - location.y * scaleV + 20);
			g.drawString(info2, p.getHitBox().getX() * scaleH - location.x * scaleH, p.getHitBox().getFarLocation().y * scaleV - location.y * scaleV + 35);
			g.drawRect(p.getHitBox().getX() * sH - location.x * sH, p.getHitBox().getY() * sV - location.y * sV, p.getHitBox().getWidth() * sH + sH, p.getHitBox().getHeight() * sV + sV);
		}
		
	}
}
