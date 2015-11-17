package game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics2D;

public class Room {

	private int exitType;
	private String name;
	private Dimension size;
	private Tile[][] tiles;

	public Room(int x, int y){
		
		tiles = new Tile[y][x];

		for (int i = 0; i < y; i++){

			for (int j = 0; j < x; j++){

				if (j == 0 && i == 0){

					tiles[i][j] = new Tile(CollisionType.WALL, 0, "CORNER_WALL");

				} else if (j == 0 && (i == y - 1)){

					tiles[i][j] = new Tile(CollisionType.WALL, 0, "CORNER_WALL")

				}
			}
		}
	}
}
