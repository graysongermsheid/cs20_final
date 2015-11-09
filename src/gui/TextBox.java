package gui;

import resources.ResourceManager;
import resources.SpriteFont;
import java.awt.Graphics2D;
import resources.Animation;
import input.InputHandler;
import java.io.*;


public class TextBox extends GUIMenu {

	private String[] lines;
	private int currentLine;
	private int currentLetter;
	private Animation indicatior;
	private float scrollSpeed;
	private double timer;
	private boolean reachedEndOfLine;
	private boolean reachedEndOfMessage;
	private boolean actionHeldThrough;
	
	public TextBox(String messageFile, int x, int y, int width, int height){

		super("composite_one.png", x, y, width, height);

		font = ResourceManager.getFont("font.png");

		indicatior = new Animation(ResourceManager.getSpriteSheet("textboxProceed.png"), 1.0f);

		loadMessage(messageFile);

		scrollSpeed = 0.25f;
		timer = 0d;
		reachedEndOfLine = false;
		actionHeldThrough = false;

	}

	public void show(){

		if (!isVisible()){
			timer = 0d;
			reachedEndOfLine = false;
			reachedEndOfMessage = false;
			setVisible(true);

			currentLetter = 0;
			currentLine = 0;
		}

	}

	private void loadMessage(String fileName){

		try {

			BufferedReader reader = new BufferedReader(new FileReader("content/" + fileName));

			lines = new String[1];
			String line;
			int i = 0;

			while ((line = reader.readLine()) != null){

				String[] x = new String[i + 1];

				for (int j = 0; j < lines.length; j++){

					x[j] = lines[j];

				}

				x[i] = line;

				lines = x;

				i++;
			}

		} catch (Exception e){

			System.out.println("Could not read message file <" + fileName + ">");

		}
	}

	@Override
	public void update(double elapsedMilliseconds){

		indicatior.update(elapsedMilliseconds);

		timer += elapsedMilliseconds;

		if (timer >= 1000 * scrollSpeed){

			timer = 0d;

			if ((currentLetter < lines[currentLine].length()) && !reachedEndOfLine){

				if (currentLetter < lines[currentLine].length() - 1){

					String line = lines[currentLine];

					currentLetter = (line.substring(currentLetter + 1, currentLetter + 2).equals(" ")) ? currentLetter + 2 : currentLetter + 1;

				} else {

					currentLetter++;

				}

			} else if (!reachedEndOfLine){

				reachedEndOfLine = true;
				indicatior.setFrame(0);

			}

		}

		if ((currentLine == lines.length - 1) && reachedEndOfLine){

			reachedEndOfMessage = true;

		}
	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED && !reachedEndOfLine){

			scrollSpeed = 0.05f;
			actionHeldThrough = true;

		} else if (InputHandler.KEY_ACTION2_PRESSED && reachedEndOfLine && !reachedEndOfMessage && !actionHeldThrough){

			System.out.println("advance line");

			currentLine++;
			currentLetter = 0;
			reachedEndOfLine = false;
			actionHeldThrough = true;

		} else if (InputHandler.KEY_ACTION2_PRESSED && reachedEndOfMessage && !actionHeldThrough){

			setVisible(false);

		} else if (!InputHandler.KEY_ACTION2_PRESSED){

			actionHeldThrough = false;
			scrollSpeed = 0.25f;

		}
	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

		for (int i = 0; i < currentLetter; i++){

			font.setColor(java.awt.Color.GREEN);
			font.drawText(lines[currentLine].substring(0, currentLetter), location.x + 7, location.y + 5, g);

		}

		if (reachedEndOfLine){

			g.drawImage(indicatior.currentFrame(), location.x + size.width - (indicatior.getSize().width + 4), location.y + size.height - (indicatior.getSize().height + 4), null);

		}
	}
}