package game;

public class Tile {
	
	private String name;
	//collision type
	private Dimension size;
	private Point location;
	private int spriteID;
	private SpriteSheet sheet;

	public Tile(String name, int x, int y, int width, int height, int spriteID, String spriteSheet){

		this.name = name;
		this.location = new Point(x, y);
		this.spriteID = spriteID;
		this.sheet = ResourceManager.getSpriteSheet(spriteSheet);
		this.size = new Dimension(width, height);

	}

	public void draw(Graphics2D g){

		g.drawImage(sheet.getImage(spriteID), location.x, location.y, null);

	}
}