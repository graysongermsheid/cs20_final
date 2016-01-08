package game;

import level.AABB;
import resources.SpriteSheet;
import resources.ResourceManager;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Entity {

	protected AABB boundingBox;
	protected EntityType entityType;
	protected SpriteSheet sourceSprites;
	
	public Entity(int x, int y, int width, int height, String spriteSheet){
		
		this.boundingBox = new AABB(x, y, width, height);
		this.sourceSprites = ResourceManager.getSpriteSheet(spriteSheet);
		
	}
	
	protected EntityType getType(){
		
		return entityType;
		
	}
	
	public Point getCenter(){
		
		return new Point(boundingBox.getFarLocation().x - boundingBox.getSize().width / 2, boundingBox.getFarLocation().y - boundingBox.getSize().height / 2);
		
	}
	
	public abstract void update(double elapsedMilliseconds);
	public abstract void draw(Graphics2D g);
	protected abstract void collide(Entity e);
	
}
