package level;

import java.awt.Graphics2D;
import level.CollisionLayer.CollisionType;

public class Level {
	
	protected int height;
	protected int width;
	protected String name;
	protected String tileSet;

	private TileLayer[] layers;
	//private ArrayList<Entity> entities;
	private CollisionLayer collisions;

	public Level(int width, int height, String name, String tileSet){

		this.width = width;
		this.height = height;
		this.name = name;
		this.tileSet = tileSet;
		layers = new TileLayer[0];

	}

	public void addLayer(){

		TileLayer l2[] = new TileLayer[layers.length + 1];

		for (int i = 0; i < layers.length; i++){

			l2[i] = layers[i];

		}

		l2[layers.length] = new TileLayer((tileSet == null) ? "default.png" : tileSet, width, height);
		layers = l2;

	}

	public void addTile(Tile t, int x, int y){

		layers[layers.length - 1].addTile(t, x, y);

	}

	public void addCollision(int x, int y, CollisionType type){

		collisions.set(x, y, type);

	}

	public void draw(Graphics2D g){

		for (TileLayer t : layers){

			t.draw(g);

		}
	}
}