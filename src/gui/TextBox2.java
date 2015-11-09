package gui;

import resources.ResourceManager;
import resources.Animation;
import input.InputHandler;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class TextBox2 extends GUIMenu {

	private String[] backlog;
	private ArrayList<String> message;
	private Dimension displayArea;
	private Animation indicator;

	private double timer;
	private double delay;

	private int currentLine;
	private int currentLetter;

	private boolean reachedPause;
	private boolean clearNext;
	private boolean finishedMessage;
	private boolean actionHeld;
	private Color bestFontColor;

	public TextBox2(String messageFile, int x, int y, int width, int height){

		super("composite_two.png", x, y, width, height);
		this.font = ResourceManager.getFont("font.png");

		message = new ArrayList<String>();
		loadMessage(messageFile);

		timer = 0d;
		delay = 0.10d;
		
		displayArea = new Dimension(size.width - 12, size.height - 12);
		indicator = new Animation(ResourceManager.getSpriteSheet("textboxArrow.png"), 0.75f);

		bestFontColor = background.getBestFontColor();
	}

	public void show(){

		if (isVisible()){return;}
		
		setVisible(true);
		timer = 0d;

		reachedPause = false;
		finishedMessage = false;
		actionHeld = false;
		clearNext = false;
		currentLetter = 0;
		currentLine = 0;

	}

	private void loadMessage(String fileName){

		try {

			BufferedReader reader = new BufferedReader(new FileReader("content/" + fileName));
			String thisLine;
			String whole = "";

			while ((thisLine = reader.readLine()) != null){

				whole += " " + thisLine;

			}

			whole = whole.trim();

			message = splitMessage(whole);

		} catch (FileNotFoundException e) {

			System.out.println("ERROR: couldn't load file <" + fileName + ">");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("ERROR: Unknown exception in TextBox2");

		}

	}

	private ArrayList<String> splitMessage(String source){

		String[] words = source.split(" ");
		ArrayList<String> lines = new ArrayList<String>();
		int index = 0;

		for (int i = 0; i < words.length; i++){

			if (lines.size() == 0 || lines.get(index) == null){

				lines.add(index, words[i]);

			} else if (!(font.getStringSize(lines.get(index) + " " + words[i]).width > size.width - 10)){

				lines.set(index, lines.get(index) + " " + words[i]);

			} else if (font.getStringSize(lines.get(index) + " " + words[i]).width > size.width - 10){

				if (font.getStringSize(words[i]).width > size.width - 10){
					
					ArrayList<String> splitWords = splitWord(words[i]);
					
					for (String s : splitWords){
						
						index++;
						lines.add(index, s);
						
					}
					
				} else {
					
					index++;
					lines.add(index, words[i]);

				}
			}
		}

		return lines;

	}
	
	private ArrayList<String> splitWord(String word){
		
		ArrayList<String> words = new ArrayList<String>();
		int currentWord = 0;
		
		for (int i = 0; i < word.length(); i++){
			
			if (words.size() == 0 || words.get(currentWord) == null){
				
				words.add(currentWord, word.substring(i, i + 1));
				
			} else if ((i + 2 < word.length()) && (font.getStringSize(words.get(currentWord) + word.substring(i, i + 2)).width > size.width - 10)) {
				
				words.set(currentWord, words.get(currentWord) + "-");
				currentWord++;
				words.add(currentWord, word.substring(i, i + 1));
				
			} else {
				
				words.set(currentWord, words.get(currentWord) +  word.substring(i, i + 1));
				
			}
		}
		
		return words;
	}

	@Override
	public void update(double elapsedMilliseconds){

		if (!visible){ return; }

		indicator.update(elapsedMilliseconds);

		timer += elapsedMilliseconds;

		if (timer >= 1000 * delay){
			
			timer = 0d;

			if (currentLetter < message.get(currentLine).length() && !reachedPause){

				if (currentLetter < message.get(currentLine).length() - 1){

					currentLetter = (message.get(currentLine).substring(currentLetter + 1, currentLetter + 2).equals(" ")) ? currentLetter + 2 : currentLetter + 1;

				} else {

					currentLetter++;

				}
			}

			if (currentLine == message.size() - 1){

				finishedMessage = true;

			} else if (currentLetter == message.get(currentLine).length()){

				currentLine++;
				currentLetter = 0;

			} 

			if (currentLetter < message.get(currentLine).length()){

				if (message.get(currentLine).substring(currentLetter, currentLetter + 1).equals("`")){

					message.set(currentLine, message.get(currentLine).replace("`", ""));
					reachedPause = true;

				} else if (message.get(currentLine).substring(currentLetter, currentLetter + 1).equals("^")){

					message.set(currentLine, message.get(currentLine).replace("^", ""));
					clearNext = true;
					reachedPause = true;

				}
			}
		}
	}

	@Override
	public void processInput(){

		if (!visible){ return; }

		if (InputHandler.KEY_ACTION2_PRESSED){

			if (!actionHeld){

				actionHeld = true;

				if (finishedMessage && currentLetter >= message.get(currentLine).length() - 1){

					setVisible(false);

				} else if (reachedPause){

					reachedPause = false;

				}
			}

			delay = 0.025d;

		} else {

			actionHeld = false;
			delay = 0.10d;

		}
	}

	@Override
	public void draw(Graphics2D g){

		super.draw(g);

		int i = 0;

		if (currentLine > 0){

			for (i = 0; i < currentLine; i++){

				font.setColor(bestFontColor);
				font.drawText(message.get(i), location.x + 8, location.y + 8 + (i * font.getOriginalSize().height), g);

			}
		}
		font.setColor(bestFontColor);
		font.drawText(message.get(currentLine).substring(0, currentLetter), location.x + 8, location.y + 8 + (i * font.getSize().height), g);
	
		if (reachedPause || (currentLetter >= message.get(currentLine).length() - 1 && currentLine >= message.size() - 1)){

			g.drawImage(indicator.currentFrame(), location.x + size.width - 16, location.y + size.height - 16, null);

		}
	}
}