package resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageTools {

	public static BufferedImage combineImages(int width, int height, BufferedImage... images){
		
		if (images.length < 9){
			
			System.out.println("ERROR: Array size too small to combine images");
			return null;
	
		}
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage currentImage = null;
		Graphics2D imageGraphics = newImage.createGraphics();
		
		int frameWidth = images[0].getWidth();
		int frameHeight = images[0].getHeight();
		int framesWide = width / frameWidth;
		int framesHigh = height / frameHeight;
		
		for (int i = 0; i < framesHigh; i++){
			
			for (int j = 0; j < framesWide; j++){
				
				if (i == 0 && j == 0){
					
					currentImage = images[0];
					
				} else if (i == framesHigh - 1 && j == 0){
					
					currentImage = images[6];
					
				} else if (i == 0 && j == framesWide - 1){
					
					currentImage = images[2];
					
				} else if (i == framesHigh - 1 && j == framesWide - 1){
					
					currentImage = images[8];
					
				} else if (i == 0){
					
					currentImage = images[1];
					
				} else if (i == framesHigh - 1){
					
					currentImage = images[7];
					
				} else if (j == 0){
					
					currentImage = images[3];
					
				} else if (j == framesWide - 1){
					
					currentImage = images[5];
					
				} else {
					
					currentImage = images[4];
					
				}
				
				imageGraphics.drawImage(currentImage, j * frameWidth, i * frameHeight, null);
				
			}
		}
		
		return newImage;
		
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

		return combineImages(newWidth, newHeight, splitImages);

	}
	
	public static Color getAverageColor(BufferedImage b){
		
		int averageR, averageG, averageB;
		averageR = 0;
		averageG = 0;
		averageB = 0;
		
		for (int i = 0; i < b.getHeight(); i++){
			
			for (int j = 0; j < b.getWidth(); j++){
				
				int rgb = b.getRGB(j, i);
				
				averageB += (rgb) & 0xff;
				averageG += (rgb >> 8) & 0xff;
				averageR += (rgb >> 16) & 0xff;
				
			}
		}
		
		int numPixels = b.getWidth() * b.getHeight();
		
		averageR /= numPixels;
		averageG /= numPixels;
		averageB /= numPixels;
		
		return new Color(averageR, averageG, averageB);
	}
	
	public static Color getInverseColor(Color c){
		
		int rgb = c.getRGB();
		
		float alpha = ((rgb >>> 24) & 0xff) / 255;
		float red = ((rgb >> 16) & 0xff) / 255;
		float green = ((rgb >> 8) & 0xff) / 255;
		float blue = ((rgb) & 0xff) / 255;
		
		alpha = Math.abs(alpha - 1.0f);
		red = Math.abs(red - 1.0f);
		green = Math.abs(green - 1.0f);
		blue = Math.abs(blue - 1.0f);
		
		return new Color(alpha, red, green, blue);
		
	}
}
