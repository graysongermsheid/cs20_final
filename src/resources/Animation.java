package resources;

import java.awt.image.BufferedImage;

public class Animation extends SpriteSheet {
	
	private double delay;
	private double timer;
	private int minFrame;
	private int maxFrame;
	private int currentFrame;
	private boolean paused;

	public Animation(SpriteSheet source, double delay){

		super(source);

		this.delay = delay;
		this.timer = 0d;
		this.currentFrame = 0;

		this.minFrame = 0;
		this.maxFrame = images.length - 1;

	}

	public Animation(double delay, BufferedImage... images){
		
		this.images = images;
		this.delay = delay;
		this.timer = 0d;
		this.currentFrame = 0;

		this.minFrame = 0;
		this.maxFrame = images.length - 1;
		
	}
	
	public BufferedImage getCurrentFrame(){

		return images[currentFrame];

	}

	public void setCurrentFrame(int frame){

		currentFrame = frame;
		timer = 0d;

	}

	public void advanceCurrentFrame(){

		currentFrame = (currentFrame == maxFrame) ? minFrame : currentFrame + 1;

	}

	public void setRange(int start, int end){

		minFrame = start;
		maxFrame = end;

	}

	public BufferedImage getFrame(int index){

		return images[index];

	}

	public void setFrame(int index, BufferedImage replacement){

		images[index] = replacement;

	}

	public void pause(){

		paused = true;

	}

	public void setPaused(boolean value){

		paused = value;

	}

	public void update(double elapsedMilliseconds){
		
		if (!paused){

			timer += elapsedMilliseconds;
			
			if (timer >= delay * 1000){

				advanceCurrentFrame();
				timer = 0d;

			}
		}
	}
}