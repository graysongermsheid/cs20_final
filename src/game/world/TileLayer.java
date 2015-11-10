package game.world;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class TileLayer {
	
	private Tile[] tiles;

	public TileLayer(ArrayList<Tile> tiles){

		this.tiles = new Tile[tiles.size()];
		this.tiles = tiles.toArray(this.tiles);

	}

	public Tile[] getTiles(){

		return tiles;

	}

	public void update(double elapsedMilliseconds){

		for (Tile t : tiles){

			//t.update(elapsedMilliseconds);

		}
	}

	public void draw(Graphics2D g){

		for (Tile t : tiles){

			t.draw(g);

		}
	}
}