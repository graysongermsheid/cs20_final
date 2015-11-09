package resources;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Point;
import java.io.*;
import javax.imageio.*;
import java.util.Arrays;

public class SpriteSheet {
	
	//private BufferedImage source;
	protected BufferedImage[] images;
	protected Dimension frameSize;
	protected String name;

	protected SpriteSheet(){}

	public SpriteSheet(String fileName, int imageWidth, int imageHeight){

		name = fileName;
		frameSize = new Dimension(imageWidth, imageHeight);
		splitImage(fileName);

	}

	public SpriteSheet(SpriteSheet copyFrom){

		name = copyFrom.name;
		frameSize = copyFrom.frameSize;
		images = copyFrom.images.clone();

	}

	public SpriteSheet getRange(int start, int end){

		SpriteSheet newSheet = new SpriteSheet();

		newSheet.name = this.name;
		newSheet.frameSize = this.frameSize;
		newSheet.images = new BufferedImage[end - start + 1];

		for (int i = start; i <= end; i++){

			newSheet.images[i - start] = this.images[i];

		}

		return newSheet;

	}

	public String getName(){

		return name;

	}

	public BufferedImage getImage(int index){

		return images[index];

	}

	private void splitImage(String fileName){

		try {

			BufferedImage source = ImageIO.read(new File("content/" + fileName));

			int sourceWidthFrames = source.getWidth() / frameSize.width;
			int sourceHeightFrames = source.getHeight() / frameSize.height;

			images = new BufferedImage[sourceHeightFrames * sourceWidthFrames];

			for (int i = 0; i < sourceHeightFrames; i++){

				for (int j = 0; j < sourceWidthFrames; j++){

					images[i * sourceWidthFrames + j] = source.getSubimage(j * frameSize.width,
																		i * frameSize.height,
																		frameSize.width,
																		frameSize.height);
				}
			}

		} catch (IOException e){

			System.out.println("Could not load image <" + fileName + ">");
			return;

		}
	}

	public Dimension getSize(){

		return frameSize;

	}
}