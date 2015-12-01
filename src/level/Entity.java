package level;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import resources.Animation;

public abstract class Entity {
	
	protected AABB bounds;
	protected Animation animations;

	public void update(double elapsedMilliseconds){

		animations.update(elapsedMilliseconds);

	}

	public void draw(Graphics2D g){

		g.drawImage(animations.getCurrentFrame(), bounds.getLocation().x, bounds.getLocation().y, null);

	}

	protected abstract void processCollision(Entity e);
	protected abstract void processCollision(int c);
}