package level;

import resources.ResourceManager;
import resources.SpriteSheet;
import java.awt.Graphics2D;

public class TileLayer {
	
	private Tile[][] tiles;
	private SpriteSheet spritesheet;

	public TileLayer(String spritesheet, int width, int height){

		tiles = new Tile[height][width];
		this.spritesheet = ResourceManager.getSpriteSheet(spritesheet);

	}

	public void addTile(Tile t, int x, int y){

		tiles[y][x] = t;

	}

	public void draw(Graphics2D g){

		for (int i = 0; i < tiles.length; i++){

			for (int j = 0; j < tiles[0].length; j++){

				g.drawImage(spritesheet.getImage(tiles[i][j].getSpriteID()), j * spritesheet.getSize().width, i * spritesheet.getSize().height, null);

			}
		}
	}
}