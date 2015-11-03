package gui;

import resources.ResourceManager;
import input.InputHandler;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Color;

public class TextBox2 extends GUIMenu {
	
	private String[] phrases;
	private String[] thisPhrase;

	private int currentPhrase;
	private int currentLetter;
	private int currentLine;

	private boolean reachedEndOfLine;
	private boolean reachedEndOfPhrase;
	private boolean actionHeldThrough;

	private double timer;
	private double delay;

	public TextBox2(String message, int x, int y, int width, int height){

		super("composite_one.png", x, y, width, height);

		this.font = ResourceManager.getFont("font.png");

		loadMessage(message);

		reachedEndOfLine = false;
		reachedEndOfPhrase = false;
		actionHeldThrough = false;

		timer = 0d;
		delay = 0.25d;
	}

	public void show(){

		setVisible(true);

		currentLine = 0;
		currentLetter = 0;
		currentPhrase = 0;

		reachedEndOfLine = false;
		reachedEndOfPhrase = false;
		actionHeldThrough = false;

		timer = 0d;
		delay = 0.25d;

		thisPhrase = splitPhrase(phrases[0]);
	}

	private void loadMessage(String fileName){

		try {

			BufferedReader br = new BufferedReader(new FileReader("content/" + fileName));
			String line;
			String message = "";

			while ((line = br.readLine()) != null){

				message += line + " ";

			}

			phrases = message.split(";");
			thisPhrase = splitPhrase(phrases[0]);

		} catch (FileNotFoundException e) {

			System.out.println("          AAAAAAAAAAAAAAAAA");

		}catch (Exception e){

			e.printStackTrace();
			System.out.println("couldn't load message");

		}
	}

	@Override
	public void update(double elapsedMilliseconds){

		timer += elapsedMilliseconds;

		if (timer >= 1000 * delay){

			timer = 0d;
			updatePhrase();

		}

	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED){

			delay = 0.05d;

			if (reachedEndOfLine && !actionHeldThrough){

				if ((currentLine == thisPhrase.length - 1) && (currentLetter == thisPhrase[currentLine].length() - 1)){

					reachedEndOfPhrase = true;

				} else {

					currentLine++;
					currentLetter = 0;

				}
			}

			if (reachedEndOfPhrase && !actionHeldThrough){

				if ((currentPhrase == phrases.length - 1) &&
					(currentLine == thisPhrase.length - 1) &&
					(currentLetter == thisPhrase[currentLine].length() - 1)){

					setVisible(false);

				} else {

					currentPhrase++;
					currentLetter = 0;
					currentLine = 0;
					thisPhrase = splitPhrase(phrases[currentPhrase]);

				}
			}

			actionHeldThrough = true;

		} else {

			delay = 0.25d;
			actionHeldThrough = false;

		}
	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

		if (currentLine == 0){

			font.drawColoredText(thisPhrase[currentLine].substring(0, currentLetter), location.x + 5, location.y + 5, Color.RED, g);

		} else {

			font.drawColoredText(thisPhrase[currentLine - 1], location.x + 5, location.y + 5, Color.GREEN, g);
			font.drawColoredText(thisPhrase[currentLine].substring(0, currentLetter), location.x + 5, location.y + 25, Color.RED, g);

		}

	}

	private void updatePhrase(){

		if (currentLetter < thisPhrase[currentLine].length() - 1){

			currentLetter++;

		} else {

			reachedEndOfLine = true;

		}
	}

	private String[] splitPhrase(String phrase){

		String splitLines[] = new String[1];
		splitLines[0] = "";
		int currentLine = 0;
		int currentLineLength = 0;
		int lastLineEnd = 0;

		String[] words = phrase.split(" ");

		for (String word : words){

			if (font.widthOfString(splitLines[currentLine] + " " + word) > size.width - 10){

				currentLine++;
				splitLines = extendArray(splitLines);

			}

			splitLines[currentLine] += " " + word;

		}

		for (String s : splitLines){

			System.out.println(s);

		}

		return splitLines;
	}

	private String[] extendArray(String[] array){

		String[] array2 = new String[array.length + 1];

		for (int i = 0; i < array.length; i++){

			array2[i] = array[i];

		}

		array2[array2.length - 1] = "";

		return array2;
	}

	private int lastTerminator(String s){

		for (int i = s.length(); i > 0; i--){

			if (s.substring(i - 1, i).equals(" ") ||
				s.substring(i - 1, i).equals(".") ||
				s.substring(i - 1, i).equals(",") ||
				s.substring(i - 1, i).equals(";") ||
				s.substring(i - 1, i).equals(":")){

				System.out.println("lastTerminator: " + s.substring(i - 1, i) + " (" + i + ")");

				return i - 1;

			}
		}

		System.out.println("-11111111");
		return -1;
	}
}