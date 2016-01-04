package game;

import level.AABB;
import resources.SpriteSheet;
import resources.ResourceManager;
import java.awt.Graphics2D;

public abstract class Entity {

	protected AABB boundingBox;
	protected EntityType entityType;
	protected SpriteSheet sourceSprites;
	
	public Entity(int x, int y, int width, int height, String spriteSheet){
		
		this.boundingBox = new AABB(x, y, width, height);
		this.sourceSprites = ResourceManager.getSpriteSheet(spriteSheet);
		
	}
	
	public abstract void collide(Entity e);
	
	protected EntityType getType(){
		
		return entityType;
		
	}
	
	public abstract void update(double elapsedMilliseconds);
	public abstract void draw(Graphics2D g);
	
}
