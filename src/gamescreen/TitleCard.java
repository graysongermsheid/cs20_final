package gamescreen;

import java.awt.image.BufferedImage;
import resources.ResourceManager;

public class TitleCard implements GameScreen {
	
	private BufferedImage titleCard;

	public void update(double elapsedMilliseconds);

	public void draw(Graphics2D g);

	public void processInput();

	public String getTitle();

	public void loadResources();

	public void unloadResources();

}