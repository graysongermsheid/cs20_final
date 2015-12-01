pacakge level;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Entity {
	
	protected AABB bounds;
	protected Animation animations;

	public abstract void update(double elapsedMilliseconds){

		animations.update(elapsedMilliseconds);

	}

	public void draw(Graphics2D g){

		g.drawImage(animations.getCurrentFrame(), bounds.getLocation().x, bounds.getLocation().y, null);

	}

	protected abstract void processCollision(Entity e);
	protected abstract void processCollision(int c);
}