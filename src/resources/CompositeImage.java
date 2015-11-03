package resources;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class CompositeImage extends SpriteSheet {

	BufferedImage compositeImage;

	private Dimension totalSize;

	public CompositeImage(String spriteSheet, int frameWidth, int frameHeight, int totalWidth, int totalHeight){

		super(spriteSheet, frameWidth, frameHeight);

		/*totalWidth -= (totalWidth % frameWidth);
		totalHeight -= (totalHeight % frameHeight);*/

		totalSize = new Dimension(totalWidth, totalHeight);

		compositeImage = createCompositeImage();

	}

	public CompositeImage(SpriteSheet copyFrom, int totalWidth, int totalHeight){

		super(copyFrom);

		totalSize = new Dimension(totalWidth, totalHeight);

		compositeImage = createCompositeImage();

	}

	public BufferedImage getDrawImage(){

		return compositeImage;

	}

	private BufferedImage createCompositeImage(){

		int height = this.frameSize.height;
		int width = this.frameSize.width;
		int totalHeight = totalSize.height;
		int totalWidth = totalSize.width;
		int framesWide = totalWidth / width;
		int framesHigh = totalHeight / height;

		BufferedImage combinedImage = new BufferedImage(width * (totalWidth / width), height * (totalHeight / height), this.images[0].getType());
		BufferedImage drawImage;
		Graphics2D imageGraphics = combinedImage.createGraphics();

		System.out.println("creating composite " + totalSize.width / this.frameSize.width + "x" + totalSize.height / this.frameSize.height);

		for (int i = 0; i < framesHigh; i++){

			for (int j = 0; j < framesWide; j++){

				if ((i == 0) && (j == 0)){

					drawImage = images[0];

				} else if ((i == 0) && (j == framesWide - 1)){

					drawImage = images[2];

				} else if ((i == framesHigh - 1) && (j == 0)){

					drawImage = images[6];

				} else if ((i == framesHigh - 1) && (j == framesWide - 1)){

					drawImage = images[8];

				} else if ((i == 0) && (j != 0)){

					drawImage = images[1];

				} else if ((i != 0) && (j == 0)){

					drawImage = images[3];

				} else if ((i == framesHigh - 1) && (j != 0)){

					drawImage = images[7];

				} else if ((i != 0) && (j == framesWide - 1)){

					drawImage = images[5];

				} else {

					drawImage = images[4];

				}

				imageGraphics.drawImage(drawImage, j * width, i * height, null);
				
			}
		}

		imageGraphics.dispose();
		return combinedImage;
	}
}