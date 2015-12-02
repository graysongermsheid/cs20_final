package level;

import resources.ResourceManager;
import resources.SpriteSheet;
import java.awt.Graphics2D;
import java.awt.Point;

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

	public void draw(Point p0, Point p1, Graphics2D g){
				
		int x0 = (int)Math.floor(p0.x / spritesheet.getSize().width); //get the minimum x tile (so the screen is 100% covered)
		int y0 = (int)Math.floor(p0.y / spritesheet.getSize().height);
		int x1 = (int)Math.ceil(p1.x / spritesheet.getSize().width);
		int y1 = (int)Math.ceil(p1.y / spritesheet.getSize().height);
		
		x0 = Math.max(x0, 0);
		y0 = Math.max(y0, 0);
		x1 = Math.min(x1 + 1, tiles[0].length);
		y1 = Math.min(y1 + 1, tiles.length);
		
		//p0.translate(p0.x - x0, p0.y - y0);
		
		Point px = new Point(p0.x - x0, p0.y - y0);
		
		for (int i = y0; i < y1; i++){

			for (int j = x0; j < x1; j++){

				g.drawImage(spritesheet.getImage(tiles[i][j].getSpriteID()), (j - x0) * spritesheet.getSize().width - (px.x % 16), (i - y0) * spritesheet.getSize().height - (px.y % 16), null);

			}
		}
	}
}