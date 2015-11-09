package gui;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import resources.SpriteSheet;
import resources.ResourceManager;

public abstract class GUIComponent {
	
	protected Point location;
	protected Dimension size;
	protected SpriteSheet source;
	protected boolean visible;

	protected GUIComponent(String imageName, int x, int y, int width, int height){

		source = ResourceManager.getSpriteSheet(imageName);
		size = new Dimension(width, height);
		location = new Point(x, y);

	}

	public abstract void draw(Graphics2D g);

	public abstract void update(double elapsedMilliseconds);

	public abstract void processInput();

	public boolean isVisible(){

		return visible;

	}

	public void setVisible(boolean visibility){

		visible = visibility;

	}

	public Point getLocation(){

		return location;

	}

	public Dimension getSize(){

		return size;

	}

	public boolean isInsideBoundaries(Point p){

		if (p.x > location.x &&
			p.x < location.x + size.width &&
			p.y > location.y &&
			p.y < location.y + size.height){

			return true;

		}

		return false;
	}
}