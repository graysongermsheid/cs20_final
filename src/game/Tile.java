package game;

public class Tile {
	
	private String name;
	private int spriteID;

	public Tile(String name, int spriteID){

		this.name = name;
		this.spriteID = spriteID;

	}

	public int getSpriteID(){

		return spriteID;

	}
}