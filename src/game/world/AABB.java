package game.world;

import java.awt.Point;

public class AABB {
	
	public static boolean collides(Entity e, Tile t){

		switch (t.getCollisionType()){

			Point a = e.getLocation();
			Point b = e.getFarLocation();

			case 0xffff:

				return checkCollision(a, b, t.getLocation(), t.getFarLocation());

			case 0x0000:

				return false;

			default:

				return true;

		}

	}

	private boolean checkCollision(Point aMin, aMax, bMin, bmax){

		if (aMin.x > bMax.x ||
			aMin.y > bMax.y ||
			aMax.x < bMin.x ||
			aMax.y < bMin.y){

			return false;

		}

		return true;

	}

	public static int overlap(int aMax, int aMin, int bMax, bMin){

		if (aMax > bMin){

			return aMax - bMin;

		} else if (aMin < bMax){

			return aMin - bMax;

		}

		return 0;

	}
}