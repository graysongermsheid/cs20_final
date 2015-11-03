package resources;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.awt.Color;

public class SpriteFont extends SpriteSheet{
	
	private HashMap<String, Integer> keyMap = new HashMap<String, Integer>();

	public SpriteFont(SpriteSheet source){

		super(source);
		initialize();

	}

	public void drawText(String text, int x, int y, Graphics2D g){

		for (int i = 0; i < text.length(); i++){

			g.drawImage(images[keyMap.get(text.substring(i, i + 1))], x, y, null);
			x += images[keyMap.get(text.substring(i, i + 1))].getWidth();

		}

	}

	public void drawColoredText(String text, int x, int y, Color c, Graphics2D g){

		for (int i = 0; i < text.length(); i++){

			g.drawImage(coloredImage(images[keyMap.get(text.substring(i, i + 1))], c), x, y, null);
			x += images[keyMap.get(text.substring(i, i + 1))].getWidth();

		}

	}

	private void initialize(){

		keyMap.put("	", 0);
		keyMap.put("!", 1);
		keyMap.put("\"", 2);
		keyMap.put("#", 3);
		keyMap.put("$", 4);
		keyMap.put("%", 5);
		keyMap.put("&", 6);
		keyMap.put("'", 7);
		keyMap.put("(", 8);
		keyMap.put(")", 9);
		keyMap.put("*", 10);
		keyMap.put("+", 11);
		keyMap.put(",", 12);
		keyMap.put("-", 13);
		keyMap.put(".", 14);
		keyMap.put("/", 15);
		keyMap.put("0", 16);
		keyMap.put("1", 17);
		keyMap.put("2", 18);
		keyMap.put("3", 19);
		keyMap.put("4", 20);
		keyMap.put("5", 21);
		keyMap.put("6", 22);
		keyMap.put("7", 23);
		keyMap.put("8", 24);
		keyMap.put("9", 25);
		keyMap.put(":", 26);
		keyMap.put(";", 27);
		keyMap.put("<", 28);
		keyMap.put("=", 29);
		keyMap.put(">", 30);
		keyMap.put("?", 31);
		keyMap.put("@", 32);
		keyMap.put("A", 33);
		keyMap.put("B", 34);
		keyMap.put("C", 35);
		keyMap.put("D", 36);
		keyMap.put("E", 37);
		keyMap.put("F", 38);
		keyMap.put("G", 39);
		keyMap.put("H", 40);
		keyMap.put("I", 41);
		keyMap.put("J", 42);
		keyMap.put("K", 43);
		keyMap.put("L", 44);
		keyMap.put("M", 45);
		keyMap.put("N", 46);
		keyMap.put("O", 47);
		keyMap.put("P", 48);
		keyMap.put("Q", 49);
		keyMap.put("R", 50);
		keyMap.put("S", 51);
		keyMap.put("T", 52);
		keyMap.put("U", 53);
		keyMap.put("V", 54);
		keyMap.put("W", 55);
		keyMap.put("X", 56);
		keyMap.put("Y", 57);
		keyMap.put("Z", 58);
		keyMap.put("[", 59);
		keyMap.put("\\", 60);
		keyMap.put("]", 61);
		keyMap.put("^", 62);
		keyMap.put("_", 63);
		keyMap.put("`", 64);
		keyMap.put("a", 65);
		keyMap.put("b", 66);
		keyMap.put("c", 67);
		keyMap.put("d", 68);
		keyMap.put("e", 69);
		keyMap.put("f", 70);
		keyMap.put("g", 71);
		keyMap.put("h", 72);
		keyMap.put("i", 73);
		keyMap.put("j", 74);
		keyMap.put("k", 75);
		keyMap.put("l", 76);
		keyMap.put("m", 77);
		keyMap.put("n", 78);
		keyMap.put("o", 79);
		keyMap.put("p", 80);
		keyMap.put("q", 81);
		keyMap.put("r", 82);
		keyMap.put("s", 83);
		keyMap.put("t", 84);
		keyMap.put("u", 85);
		keyMap.put("v", 86);
		keyMap.put("w", 87);
		keyMap.put("x", 88);
		keyMap.put("y", 89);
		keyMap.put("z", 90);
		keyMap.put("{", 91);
		keyMap.put("|", 92);
		keyMap.put("}", 93); 
		keyMap.put("~", 94);
		keyMap.put(" ", 95);

		trimLetters();
	}

	private BufferedImage coloredImage(BufferedImage s, Color c){

		BufferedImage newImage = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = newImage.createGraphics();

		for (int i = 0; i < s.getHeight(); i++){

			for (int j = 0; j < s.getWidth(); j++){

				int alpha = s.getRGB(j, i) & 0xff000000 >>> 24;

				if (alpha == 255){

					newImage.setRGB(j, i, c.getRGB());

				} else {

					Color newColor = new Color(255, 255, 255, 0);
					newImage.setRGB(j, i, newColor.getRGB());

				}
			}
		}

		return newImage;
	}

	private void trimLetters(){

		System.out.println("Trimming SpriteFont " + this.getName());

		int i = 0;
		int largestX = 0;

		for (BufferedImage image : images){

			int lowestX = getFirstPixel(image);
			int highestX = getLastPixel(image);

			if (lowestX + highestX > frameSize.width){

				highestX -= (lowestX + highestX) - frameSize.width;

			}

			largestX = (largestX < (highestX - lowestX)) ? (highestX - lowestX) : largestX;

			/*System.out.println("Trimming image " + i + "(" + image.getWidth() + "x" + image.getHeight() + ") to " + (highestX - lowestX) + "x" + image.getHeight());
			System.out.println("lowest x is " + lowestX + ", highest x is " + highestX);*/

			try { images[i] = images[i].getSubimage(lowestX, 0, highestX, image.getHeight()); } catch (Exception e) {System.out.println("couldn't trim image " + i);}

			i++;
		}

		System.out.println("largest width of a letter: " + largestX);
	}

	private int getFirstPixel(BufferedImage image){

		for (int x = 0; x < image.getWidth(); x++){

			for (int y = 0; y < image.getHeight(); y++){

				int alpha = (image.getRGB(x, y) & 0xff000000 >>> 24);

				if (alpha != 0){

					return x;

				}
			}
		}

		return 0;
	}

	private int getLastPixel(BufferedImage image){

		for (int x = image.getWidth() - 1; x >= 0; x--){

			for (int y = image.getHeight() - 1; y >= 0; y--){

				int alpha = (image.getRGB(x, y) & 0xff000000 >>> 24);

				if (alpha != 0){

					return x;

				}
			}
		}

		return image.getWidth() - 1;
	}

	public int widthOfString(String text){

		int length = 0;

		for (int i = 0; i < text.length(); i++){

			length += images[keyMap.get(text.substring(i, i + 1))].getWidth();

		}

		return length;
	}
}
	