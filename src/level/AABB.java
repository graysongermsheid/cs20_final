package level;

import java.awt.Point;
import java.awt.Dimension;

public class AABB {
	
	int x, x1, y, y1, width, height;

	public AABB(int x, int y, int width, int height){

		this.x = x;
		this.x1 = x + width;
		this.y = y;
		this.y1 = y + height;
		this.width = width;
		this.height = height;

	}

	public boolean collides(AABB a){

		if (a.x1 < x || a.x > x1 ||
			a.y1 < y || a.y > y1){

			return false;

			}

		return true;

	}

	//returns difference between this object's y1 & a's y
	//so -1 would mean that this rectangle's far edge was 1 pixel to the left of a's near edge (no collision)
	//    1 would mean that this rectangle's far edge would overlap a's near edge by one (collision)
	public int getOverlapY(AABB a){

		return a.y - y1;

	}

	//return difference between this object's x1 & a's x
	//so -1 would mean that this rectangle's far edge was 1 pixel above a's near edge (no collision)
	//    1 would mean that this rectangle's far edge would overlap a's near edge by one (collision)
	public int getOverlapX(AABB a){

		return a.x - x1;

	}

	public Point getLocation(){

		return new Point(x, y);

	}

	public Dimension getSize(){

		return new Dimension(width, height);

	}
}