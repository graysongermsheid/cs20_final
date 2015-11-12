package resources;

import java.awt.image.BufferedImage;
import java.awt.Dimension;

public class Animation extends SpriteSheet {
	
	protected float delay;
	protected double timer;
	protected int currentFrame;

	public Animation(String fileName, int frameWidth, int frameHeight, float delay){

		super(fileName, frameWidth, frameHeight);
		this.delay = delay;
		this.currentFrame = 0;
		this.timer = 0d;

	}

	public Animation(SpriteSheet source, float delay){

		super(source);
		this.delay = delay;
		this.currentFrame = 0;
		this.timer = 0d;

	}

	public BufferedImage currentFrame(){

		return this.images[currentFrame];

	}

	public void advanceFrame(){

		currentFrame = (currentFrame < images.length - 1) ? currentFrame + 1 : 0;

	}

	public void setFrame(int index){

		currentFrame = index;

	}

	public BufferedImage getFrame(int index){

		return images[index];

	}

	public void changeFrameImage(int index, BufferedImage image){

		frameSize = new Dimension(image.getWidth(), image.getHeight());
		images[index] = image;

	}

	public Dimension getSize(){

		return this.frameSize;

	}

	public void update(double elapsedMilliseconds){

		timer += elapsedMilliseconds;

		if (timer >= delay * 1000){

			timer = 0d;
			this.advanceFrame();

		}
	}
}