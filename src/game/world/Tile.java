package game.world;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import resources.ResourceManager;

public class Tile {
	
	private BufferedImage image;
	private Dimension size;
	private int collisionType;
	private Point location;

	public Tile(int x, int y, int collisionType, String imageName){

		image = ResourceManager.getImage(imageName);
		this.collisionType = collisionType;
		this.location = new Point(x, y);

	}

	public int getCollisionType(){

		return collisionType;

	}

	public Point getLocation(){

		return location;

	}

	public Point getFarLocation(){

		return new Point(location.x + size.width, location.y + size.height);

	}

	public Dimension getSize(){

		return new Dimension(image.getWidth(), image.getHeight());

	}

	public void draw(Graphics2D g){

		g.drawImage(image, location.x, location.y, null);

	}
}