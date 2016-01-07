package level;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import game.Entity;

import java.awt.Dimension;

public class Level {
	
	protected int height;
	protected int width;
	protected String name;

	private TileLayer[] layers;
	private ArrayList<Entity> entities;
	private CollisionLayer collisions;

	public Level(int width, int height, String name){

		this.width = width;
		this.height = height;
		this.name = name;
		collisions = new CollisionLayer(width, height);
		layers = new TileLayer[0];

	}

	public CollisionLayer getCollisionLayer(){
		
		return collisions;
		
	}
	
	public void addLayer(String tileSet){

		TileLayer l2[] = new TileLayer[layers.length + 1];

		for (int i = 0; i < layers.length; i++){

			l2[i] = layers[i];

		}

		l2[layers.length] = new TileLayer((tileSet == null) ? "default.png" : tileSet, width, height);
		layers = l2;

	}

	public void addTile(Tile t, int x, int y, int layer){

		layer = (layer < layers.length) ? layer : layers.length - 1;
		layers[layer].addTile(t, x, y);

	}

	public void addCollision(int x, int y, CollisionType type){

		collisions.set(x, y, type);

	}
	
	public void addEntity(Entity e){
		
		entities.add(e);
		
	}
	
	public Entity[] getEntites(){
		
		Entity[] e = new Entity[entities.size()];
		entities.toArray(e);
		return e;
		
	}

	public void update(double elapsedMilliseconds){
		
		/*for (Entity e : entities){
			
			//e.update(elapsedMilliseconds);
			
		}*/
		
	}
	
	public void draw(Point p0, Point p1, Graphics2D g){

		for (TileLayer t : layers){

			t.draw(p0, p1, g);

		}
		
		/*for (Entity e : entities){
			
			e.draw(g);
			
		}*/
	}

	public Dimension getSize(){

		return new Dimension(width, height);

	}

	public Dimension getRealSize(){

		return new Dimension(width * 16, height * 16);

	}
}