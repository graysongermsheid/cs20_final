package resources;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

public class CompositeImage extends SpriteSheet {

	BufferedImage compositeImage;

	private Dimension totalSize;

	public CompositeImage(String spriteSheet, int frameWidth, int frameHeight, int totalWidth, int totalHeight){

		super(spriteSheet, frameWidth, frameHeight);

		totalSize = new Dimension(totalWidth, totalHeight);

		compositeImage = createCompositeImage(totalSize, images);

	}

	public CompositeImage(SpriteSheet copyFrom, int totalWidth, int totalHeight){

		super(copyFrom);

		totalSize = new Dimension(totalWidth, totalHeight);

		compositeImage = createCompositeImage(totalSize, images);

	}

	public static BufferedImage resizeImage(BufferedImage source, int newWidth, int newHeight){

		BufferedImage[] splitImages = new BufferedImage[9];

		splitImages[0] = source.getSubimage(0, 0, 8, 8);
		splitImages[1] = source.getSubimage(source.getWidth() / 2 - 4, 0, 8, 8);
		splitImages[2] = source.getSubimage(source.getWidth() - 8, 0, 8, 8);

		splitImages[3] = source.getSubimage(0, source.getHeight() / 2 - 4, 8, 8);
		splitImages[4] = source.getSubimage(source.getWidth() / 2 - 4, source.getHeight() / 2 - 4, 8, 8);
		splitImages[5] = source.getSubimage(source.getWidth() - 8, source.getHeight() / 2 - 4, 8, 8);

		splitImages[6] = source.getSubimage(0, source.getHeight() - 8, 8, 8);
		splitImages[7] = source.getSubimage(source.getWidth() / 2 - 4, source.getHeight() - 8, 8, 8);
		splitImages[8] = source.getSubimage(source.getWidth() - 8, source.getHeight() - 8, 8, 8);

		return createCompositeImage(new Dimension(newWidth, newHeight), splitImages);

	}

	public BufferedImage getDrawImage(){

		return compositeImage;

	}

	public Color getBackgroundColor(){

		BufferedImage center = images[4];

		int averageR = 0;
		int averageG = 0;
		int averageB = 0;
		int averageA = 0;

		for (int i = 0; i < center.getHeight(); i++){

			for (int j = 0; j < center.getWidth(); j++){

				int color = center.getRGB(j, i);

				averageB += ((color) & 0xff);
				averageG += ((color >> 8) & 0xff);
				averageR += ((color >> 16) & 0xff);
				averageA += ((color >>> 24) & 0xff);

			}
		}

		int pixels = center.getHeight() * center.getWidth();

		averageR /= pixels;
		averageG /= pixels;
		averageB /= pixels;
		averageA /= pixels;

		return new Color(averageR, averageG, averageB, averageA);

	}

	public Color getBestFontColor(){

		int color = getBackgroundColor().getRGB();

		float blue = ((color) & 0xff) / 255f;
		float green = ((color >> 8) & 0xff) / 255f;
		float red = ((color >> 16) & 0xff) / 255f;
		float alpha = ((color >>> 24) & 0xff) / 255f;

		red = Math.abs(red - 1.0f);
		blue = Math.abs(blue - 1.0f);
		green = Math.abs(green - 1.0f);

		return new Color(red, green, blue, alpha);

	}

	private static BufferedImage createCompositeImage(Dimension newSize, BufferedImage... sources){

		int height = sources[0].getHeight();
		int width = sources[0].getWidth();
		int totalHeight = newSize.height;
		int totalWidth = newSize.width;
		int framesWide = totalWidth / width;
		int framesHigh = totalHeight / height;

		BufferedImage combinedImage = new BufferedImage(width * (totalWidth / width), height * (totalHeight / height), sources[0].getType());
		BufferedImage drawImage;
		Graphics2D imageGraphics = combinedImage.createGraphics();

		for (int i = 0; i < framesHigh; i++){

			for (int j = 0; j < framesWide; j++){

				if ((i == 0) && (j == 0)){

					drawImage = sources[0];

				} else if ((i == 0) && (j == framesWide - 1)){

					drawImage = sources[2];

				} else if ((i == framesHigh - 1) && (j == 0)){

					drawImage = sources[6];

				} else if ((i == framesHigh - 1) && (j == framesWide - 1)){

					drawImage = sources[8];

				} else if ((i == 0) && (j != 0)){

					drawImage = sources[1];

				} else if ((i != 0) && (j == 0)){

					drawImage = sources[3];

				} else if ((i == framesHigh - 1) && (j != 0)){

					drawImage = sources[7];

				} else if ((i != 0) && (j == framesWide - 1)){

					drawImage = sources[5];

				} else {

					drawImage = sources[4];

				}

				imageGraphics.drawImage(drawImage, j * width, i * height, null);
				
			}
		}

		imageGraphics.dispose();
		return combinedImage;
	}
}