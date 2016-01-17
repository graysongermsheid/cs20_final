package game;

import level.AABB;
import resources.SpriteSheet;
import resources.ResourceManager;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Entity {

	protected AABB hitBox; //FIX SO THIS CAN CHANGE SHAPE FOR ENTITIES
	protected EntityType entityType;
	protected SpriteSheet sourceSprites;
	protected ArrayList<Entity> entitiesCollided;
	protected Point position;
	
	public Entity(int x, int y, int width, int height, String spriteSheet){
		
		this.hitBox = new AABB(x, y, width, height); //(individual entities sould redefine hitbox in constructor
		this.position = new Point(x, y);
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
	
	public AABB getHitBox(){
		
		return hitBox;
		
	}
	
	public abstract void update(double elapsedMilliseconds);
	public abstract void draw(Graphics2D g, Point p);
	protected abstract void collide(Entity e);
	public void removeCollision(Entity e){

		if ((entitiesCollided.size() > 0) && (entitiesCollided.contains(e))){
				
			entitiesCollided.remove(e);
				
		}
	}
}
