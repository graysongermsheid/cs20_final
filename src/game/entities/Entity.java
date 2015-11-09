package game.entities;

import resources.ResourceManager;
import resources.SpriteSheet;
import resources.Animation;
import game.world.Tile;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Entity {

	protected SpriteSheet sprites;
	protected Animation[] animations;
	protected Point location;
	protected Point velocity; //current speed it's going
	protected int speed; //max speed it can go
	protected Dimension size;
	protected String name;
	protected AnimationState currentAnimation;

	//Speed is measured in pixels/update

	public Entity(String spritesheet, int x, int y, int speed){

		sprites = ResourceManager.getSpriteSheet(spritesheet);
		animations = new Animation[4];
		currentAnimation = AnimationState.WALK_LEFT;

		animations[0] = new Animation(sprites.getRange(0, 2), 0.25f);
		animations[1] = new Animation(sprites.getRange(3, 5), 0.25f);
		animations[2] = new Animation(sprites.getRange(6, 8), 0.25f);
		animations[3] = new Animation(sprites.getRange(9, 11), 0.25f);

		this.speed = speed;
		location = new Point(x, y);
		velocity = new Point(0, 0);
		size = sprites.getSize();
		name = spritesheet.replace(".png", "");

	}

	public String getName(){

		return name;

	}

	public Point getLocation(){

		return location;

	}

	public Point getFarLocation(){

		return new Point(location.x + size.width, location.y + size.height);

	}

	public abstract void draw(Graphics2D g);
	public abstract void update(double elapsedMilliseconds);
}