package game.world;

import java.awt.Point;
import java.awt.Dimension;

public class AABB {
	
	protected Dimension size;
	protected Point location;
	
	public AABB(Point location, Dimension size){
		
		this.location = location;
		this.size = size;
		
	}
	
	public static boolean collides(AABB a, AABB b){
		
		Point aMin = new Point(a.location.x, a.location.y);
		Point aMax = new Point(a.location.x + a.size.width, a.location.y + a.size.height);
		Point bMin = new Point(b.location.x, b.location.y);
		Point bMax = new Point(b.location.x + b.size.width, b.location.y + b.size.height);
		
		if (aMax.x < bMin.x ||
			aMax.y < bMin.y ||
			aMin.x > bMax.x ||
			aMin.y > bMax.y){
			
			return false;
			
		} else {
			
			return true;
			
		}
	}
	
	public static Point getOverlapSize(AABB a, AABB b){
		
		Point aMin = new Point(a.location.x, a.location.y);
		Point aMax = new Point(a.location.x + a.size.width, a.location.y + a.size.height);
		Point bMin = new Point(b.location.x, b.location.y);
		Point bMax = new Point(b.location.x + b.size.width, b.location.y + b.size.height);
		
		Point overlap = new Point(0, 0);
		
		if (aMin.x < bMax.x){
			
			overlap.move(aMin.x - bMax.x, overlap.y);
			
		} else if (aMax.x > bMin.x){
			
			overlap.move(aMax.x - bMin.x, overlap.y);
			
		}
		
		if (aMin.y < bMax.y){
			
			overlap.move(overlap.x, aMin.y - bMax.y);
		
		} else if (aMax.y > bMin.y){
			
			overlap.move(overlap.x, aMax.y - bMin.y);
			
		}
		
		return overlap;
		
	}
}