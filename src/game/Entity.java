package game;

import level.AABB;
import resources.SpriteSheet;
import resources.ResourceManager;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Entity {

	protected AABB boundingBox;
	protected AABB hitBox;
	protected EntityType entityType;
	protected SpriteSheet sourceSprites;
	
	public Entity(int x, int y, int width, int height, String spriteSheet){
		
		//this.boundingBox = new AABB(x, y, width, height);
		this.hitBox = new AABB(x, y, width, height);
		this.sourceSprites = ResourceManager.getSpriteSheet(spriteSheet);
		
	}
	
	protected EntityType getType(){
		
		return entityType;
		
	}
	
	public Point getCenter(){
		
		return new Point(hitBox.getFarLocation().x - hitBox.getSize().width / 2, hitBox.getFarLocation().y - hitBox.getSize().height / 2);
		
	}
	
	public Point getFarLocation(){
		
		return hitBox.getFarLocation();
		
	}
	
	public abstract void update(double elapsedMilliseconds);
	public abstract void draw(Graphics2D g, Point p);
	protected abstract void collide(Entity e);
	
}
