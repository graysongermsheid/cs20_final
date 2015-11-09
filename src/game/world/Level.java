package game.world;

import java.awt.Graphics2D;

public interface Level {
	
	public void processInput();
	public void update(double elapsedMilliseconds);
	public void draw(Graphics2D g);

	public void load();
	public void unload();

}