package game;

import java.awt.Point;
import java.awt.Dimension;

public class AABB {
	
	private Point location;
	private Dimension size;

	public AABB(int x, int y, int width, int height){

		location = new Point(x, y);
		size = new Dimension(width, height);

	}
}