package game;

import java.awt.image.BufferedImage;
import resources.ResourceManager;
import resources.SpriteSheet;

public class Tile {
	
	private CollisionType collisionType;
	private String name;
	private BufferedImage image;

	public Tile(CollisionType collisionType, int spriteID, String name){

		this.name = name;

	}
}