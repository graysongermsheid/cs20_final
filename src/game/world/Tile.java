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

	/*
		Collision types are represented hxidecimally for
		easier reading, each digit represents a collision type
		0xf000 is top left
		0x0f00 is top right
		0x00f0 is bottom left
		0x000f is bottom right

		so the 1st byte is the top of the tile and the 2nd is the bottom
		there are some special collision values (i.e 0xdddd is a door)
	*/

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

	public Point[] getSubLocation(int location){

		Point[] points = new Point[2];

		switch(location){

			case 0xf000:

				points[0] = location;
				points[1] = Point(location.x + (size.width / 2), location.y + (size.height / 2));
				break;

			case 0x0f00:

				points[0] = new Point(location.x + (size.width / 2), location.y);
				points[1] = new Point(location.x + size.width, location.y + (size.height / 2));
				break;

			case 0x00f0:

				points[0] = new Point(location.x, location.y + (size.height / 2));
				points[1] = new Point(location.x + (size.width / 2), location.y + size.height);
				break;

			case 0x000f:

				points[0] = new Point(location.x + (size.width / 2), location.y + (size.height / 2));
				points[1] = new Point(location.x + size.width, location.y + size.heigh);
				break;

		}

		return points;

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