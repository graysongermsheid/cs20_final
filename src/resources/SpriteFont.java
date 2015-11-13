package resources;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.awt.Color;

public class SpriteFont extends SpriteSheet{

	private Color currentColor;
	private Color backgroundColor;
	private int scaling;

	public SpriteFont(SpriteSheet source){

		super(source);
		trimLetters();
		backgroundColor = Color.BLACK;
		currentColor = Color.WHITE;
		scaling = 1;
	}

	public void drawText(String text, int x, int y, Graphics2D g){

		int lastX = x;
		int character;
		BufferedImage drawImage = null;

		for (int i = 0; i < text.length(); i++){

			do {

				character = text.charAt(i);

				if (character > 127 || character < 32){

					i++;
					character = -1;

				}

			} while (character == -1);

			if (scaling != 1){

				AffineTransform transform = new AffineTransform();
				transform.scale(scaling, scaling);
				AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

				try {

					drawImage = new BufferedImage(images[character].getWidth() * scaling, images[character].getHeight() * scaling, BufferedImage.TYPE_INT_ARGB);
					drawImage = scaleOp.filter(images[character], drawImage);

				} catch (Exception e){

					System.out.println("ERROR: could not scale '" + (char)character + "'");

				}

			} else {

				drawImage = images[character];

			}

			g.drawImage(coloredImage(drawImage, currentColor), lastX, y, null);
			lastX += drawImage.getWidth() + scaling;

		}
	}

	public void drawShadowedText(String text, int x, int y, Graphics2D g){

		int lastX = x;
		int character;
		BufferedImage drawImage = null;

		for (int i = 0; i < text.length(); i++){

			do {

				character = text.charAt(i);

				if (character > 127 || character < 32){

					i++;
						character = -1;

				}

			} while (character == -1);

			if (scaling != 1){

				AffineTransform transform = new AffineTransform();
				transform.scale(scaling, scaling);
				AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); //that's a weird way to spell neighbour

				try {

					drawImage = new BufferedImage(images[character].getWidth() * scaling, images[character].getHeight() * scaling, BufferedImage.TYPE_INT_ARGB);
					drawImage = scaleOp.filter(images[character], drawImage);

				} catch (Exception e){

					System.out.println("ERROR: could not scale '" + (char)character + "'");

				}

			} else {

				drawImage = images[character];

			}

			g.drawImage(coloredImage(drawImage, currentColor, backgroundColor), lastX, y, null);
			lastX += drawImage.getWidth() + scaling;

		}
	}

	public void setScaling(int scaling){

		this.scaling = scaling;

	}

	public void setColor(Color c){

		currentColor = c;

	}

	public void setBackgroundColor(Color c){

		backgroundColor = c;

	}

	private BufferedImage coloredImage(BufferedImage s, Color c){

		BufferedImage newImage = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = newImage.createGraphics();

		for (int i = 0; i < s.getHeight(); i++){

			for (int j = 0; j < s.getWidth(); j++){

				int color = s.getRGB(j, i);
				int alpha = (color >>> 24) & 0xff;
				int red = (color >> 16) & 0xff;

				if (alpha == 255 && red == 255){

					newImage.setRGB(j, i, c.getRGB());

				} else {

					Color newColor = new Color(255, 255, 255, 0);
					newImage.setRGB(j, i, newColor.getRGB());

				}
			}
		}

		return newImage;
	}

	private BufferedImage coloredImage(BufferedImage s, Color c, Color b){

		BufferedImage newImage = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = newImage.createGraphics();

		for (int i = 0; i < s.getHeight(); i++){

			for (int j = 0; j < s.getWidth(); j++){

				int color = s.getRGB(j, i);
				int a = (color >>> 24) & 0xff;
				int r = (color >> 16) & 0xff;

				if (a == 255 && r == 255){

					newImage.setRGB(j, i, c.getRGB());

				} else if (a == 255 && r == 0) {

					newImage.setRGB(j, i, b.getRGB());

				} else {

					newImage.setRGB(j, i, 0);

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

			//largestX = (largestX < (highestX - lowestX)) ? (highestX - lowestX) : largestX;

			try {

				images[i] = images[i].getSubimage(lowestX, 0, highestX, image.getHeight());

			} catch (Exception e){

				System.out.println("couldn't trim image " + i);

			}

			i++;
		}
	}

	private int getFirstPixel(BufferedImage image){

		for (int x = 0; x < image.getWidth(); x++){

			for (int y = 0; y < image.getHeight(); y++){

				int alpha = (image.getRGB(x, y) >>> 24) & 0xff;

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

				int alpha = (image.getRGB(x, y) >>> 24) & 0xff;

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

		return new Dimension(images[0].getWidth(), images[0].getHeight());

	}

	public Dimension getStringSize(String text){

		int length = 0;

		for (int i = 0; i < text.length(); i++){

			int character = text.charAt(i);
			
			if (character < 127){
			
				length += images[text.charAt(i)].getWidth() * scaling + scaling;
				
			}

		}

		return new Dimension(length, images[0].getHeight());
	}
}
	