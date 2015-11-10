package resources;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.awt.Color;

public class SpriteFont extends SpriteSheet{

	private BufferedImage modifiedImages[];
	private Color currentColor;
	private int scaling;

	public SpriteFont(SpriteSheet source){

		super(source);
		currentColor = Color.WHITE;
		trimLetters();
		modifiedImages = images;
		scaling = 1;
	}

	public void drawText(String text, int x, int y, Graphics2D g){

		int lastX = x;

		for (int i = 0; i < text.length(); i++){

			int character;

			try {

				character = text.charAt(i);
				g.drawImage(coloredImage(modifiedImages[character], currentColor), lastX, y, null);
				lastX += modifiedImages[character].getWidth() + scaling;

			} catch (Exception e) {}
		}
	}
	
	public void drawShadowedText(String text, int x, int y, Graphics2D g){
		
		int lastX = x;

		for (int i = 0; i < text.length(); i++){

			int character;

			try {

				character = text.charAt(i);
				BufferedImage shadow = coloredImage(modifiedImages[character], Color.DARK_GRAY);
				g.drawImage(shadow, lastX + scaling, y + scaling, null);
				g.drawImage(coloredImage(modifiedImages[character], currentColor), lastX, y, null);
				lastX += modifiedImages[character].getWidth() + scaling;

			} catch (Exception e) {}
		}
	}

	public void setScaling(int scaling){

		this.scaling = scaling;

		if (scaling != 1) { 

			createScaledImages(scaling); 

		} else {

			modifiedImages = images;

		}
	}

	public void setColor(Color c){

		if (currentColor.getRGB() != c.getRGB()){

			currentColor = c;

		}
	}

	private void createScaledImages(int scaling){

		BufferedImage[] newImages = new BufferedImage[images.length];

		for (int i = 0; i < images.length; i++){

			BufferedImage scaledImage = new BufferedImage(images[i].getWidth() * scaling, images[i].getHeight() * scaling, BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < images[i].getHeight(); y++){

				for (int x = 0; x < images[i].getWidth(); x++){

					for (int y2 = 0; y2 < scaling; y2++){

						for (int x2 = 0; x2 < scaling; x2++){

							scaledImage.setRGB(x * scaling + x2, y * scaling + y2, images[i].getRGB(x, y));

						}
					}
				}
			}

			newImages[i] = scaledImage;

		}

		modifiedImages = newImages;
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

		int i = 0;
		int largestX = 0;

		for (BufferedImage image : images){

			int lowestX = getFirstPixel(image);
			int highestX = getLastPixel(image);

			if (lowestX + highestX > frameSize.width){

				highestX -= (lowestX + highestX) - frameSize.width;

			}

			largestX = (largestX < (highestX - lowestX)) ? (highestX - lowestX) : largestX;

			try { images[i] = images[i].getSubimage(lowestX, 0, highestX, image.getHeight()); } catch (Exception e) {System.out.println("couldn't trim image " + i);}

			i++;
		}
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

	public Dimension getOriginalSize(){

		return frameSize;

	}

	public Dimension getSize(){

		return new Dimension(modifiedImages[0].getWidth(), modifiedImages[0].getHeight());

	}

	public Dimension getStringSize(String text){

		int length = 0;

		for (int i = 0; i < text.length(); i++){

			//System.out.println("array length: " + modifiedImages.length + " character: " + text.charAt(i) + " value: " + (int)text.charAt(i));

			length += modifiedImages[text.charAt(i)].getWidth();

		}

		return new Dimension(length, modifiedImages[0].getHeight());
	}
}
	