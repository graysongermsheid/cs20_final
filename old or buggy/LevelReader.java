package game;

import java.io.*;
import level.Level;
import level.Tile;
import level.Door;
import level.CollisionLayer.CollisionType;

public class LevelReader {
	
	private static Level currentLevel;
	private static int currentTileY = 0;
	private static int currentTileX = 0;
	private static boolean tileData = false;
	private static boolean collisionData = false;
	private static int width = 0;
	private static int height = 0;
	
	public static Level loadLevel(String fileName){

		try {

			currentLevel = new Level();
			BufferedReader br = new BufferedReader(new FileReader("src/content/levels/" + fileName + ".lvl"));
			String line = null;
			
			while ((line = br.readLine()) != null){

				if (line.charAt(0) != '#'){
					
					if (line.charAt(0) == '>'){

						processLine(line.replace(">", ""));

					} else {

						String[] thisLine = line.split(",");

						for (String s : thisLine){

							if (collisionData){

								currentLevel.addCollision(currentTileX, currentTileY, processCollision(s));

							} else if (tileData){

								currentLevel.addTile(processTile(s), currentTileX, currentTileY);

							}
							
							currentTileX++;
						}

						currentTileX = 0;
						currentTileY++;

					}
				}
			}

		} catch (Exception e){

			e.printStackTrace();
			return null;

		}

		return currentLevel;

	}

	private static CollisionType processCollision(String line){

		int num = Integer.parseInt(line);

		switch (num){

			case 1:

				return CollisionType.SOLID;

			case 2:

				return CollisionType.DOOR;

			default:

				return CollisionType.NONE;

		}

	}

	private static Tile processTile(String line){

		if (line.matches("door//s*//S*")){

			String[] data = line.split("/");
			return new Door(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]));

		} else {

			String[] data = line.split("/");
			return new Tile(Integer.parseInt(data[0]));

		}
	}

	private static void processLine(String line){

		if (line.matches("width \\d*")){

			currentLevel.width = Integer.parseInt(line.replace("width ", ""));

		} else if (line.matches("height \\d*")){

			currentLevel.height = Integer.parseInt(line.replace("height ", ""));

		} else if (line.matches("layer\\s*\\S*")){

			tileData = true;
			collisionData = false;
			currentTileY = 0;
			currentTileX = 0;
			currentLevel.addLayer();

		} else if (line.matches("collision layer\\s*\\S*")){

			collisionData = true;
			tileData = false;

		} else if (line.matches("name \\S*")){

			currentLevel.name = line.replace("name ", "");

		} else if (line.matches("tileset \\S*")){

			currentLevel.tileSet = line.replace("tileset ", "");

		}
	}
}