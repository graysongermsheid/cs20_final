package game.world;

import java.awt.Point;

public class AABB {
	
	public static boolean collides(Point aMin, Point aMax, Point bMin, Point bMax){

		if (aMax.x < bMin.x ||
			aMin.x > bMax.y ||
			aMax.y < bMin.y ||
			aMax.y > bMax.y){

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