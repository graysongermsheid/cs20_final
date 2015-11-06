package game.entities;

import resources.ResourceManager;
import resources.SpriteSheet;
import resources.Animation;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Entity {

	protected SpriteSheet sprites;
	protected Point location;
	protected Point velocity;
	protected int speed;
	protected Dimension size;

	public Entity(String spritesheet, int x, int y, int width, int height){



	}

	public Entity(String spritesheet, int x, int y, int width, int height, float speed){



	}

	public abstract void draw(Graphics2D g);
	public abstract void update(double elapsedMilliseconds);
}