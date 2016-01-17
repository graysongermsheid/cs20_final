package window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import input.InputHandler;
import resources.ResourceManager;
import resources.SpriteFont;
import gamescreen.ScreenManager;

public class Screen extends Canvas {
	
	private BufferStrategy buffer;
	private ScreenManager screenManager;
	private SpriteFont font;
	private double fps;
	private double ups;
	public static Dimension SIZE;

	public void createBuffer(){

		this.createBufferStrategy(2);
		buffer = this.getBufferStrategy();

	}

	public void run(){

		SIZE = getSize();

		resources.ResourceManager.createSpriteSheet("font.png", 16, 16);
		font = ResourceManager.getFont("font.png");
		System.out.println("Canvas size: " + SIZE);

		InputHandler in = new InputHandler();

		this.addKeyListener(in);
		this.addMouseListener(in);
		this.addMouseMotionListener(in);
		this.addMouseWheelListener(in);

		Thread gameLoop = new Thread(new Runnable(){

			@Override
			public void run(){

				loop();

			}

		});

		gameLoop.start();

	}

	private void loop(){

		screenManager = new ScreenManager(SIZE.width, SIZE.height);

		final int UPDATES_SECOND = 30;
		final int MAX_UPDATES_FRAME = 1;
		final int LOOP_SPEED = 1000000000 / UPDATES_SECOND;

		final int FRAMES_SECOND = 60;
		final int FRAME_SPEED = 1000000000 / FRAMES_SECOND;

		double currentTime = System.nanoTime();
		double lastUpdate = System.nanoTime();
		double lastRender = System.nanoTime();
		
		int frameCount = 0;
		int updateCount = 0;
		int lastSecond = (int)(lastUpdate / 1000000000);
		fps = 0d;
		ups = 0d;

		while (true){

			currentTime = System.nanoTime();
			int updates = 0;

			processInput();

			while (updates < MAX_UPDATES_FRAME && currentTime - lastUpdate > LOOP_SPEED){

				update((currentTime - lastUpdate) / 1000000);
				lastUpdate += LOOP_SPEED;
				updates++;

			}

			if (currentTime - lastUpdate > LOOP_SPEED){

				lastUpdate = currentTime - LOOP_SPEED;

			}

			updateCount += updates;

			render();
			frameCount++;
			lastRender += FRAME_SPEED;
			int thisSecond = (int)(lastRender / 1000000000);
			if (thisSecond > lastSecond){
				
				ups = updateCount;
				fps = frameCount;
				lastSecond = thisSecond;
				frameCount = 0;
				updateCount = 0;
				
			}

			while (/*currentTime - lastUpdate < LOOP_SPEED && */currentTime - lastRender < FRAME_SPEED){

				Thread.yield();
				try { Thread.sleep(1); } catch (Exception e) {}
				currentTime = System.nanoTime();

			}
		}
	}

	private void processInput(){

		if (InputHandler.MOUSE_LOCATION == null){

			return;

		}

		screenManager.processInput();


		InputHandler.resetKeys();
	}

	private void update(double elapsedMilliseconds){

		screenManager.update(elapsedMilliseconds);

	}

	private void render(){

		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, SIZE.width, SIZE.height);

		screenManager.draw(g);
		font.setColor(Color.GREEN);
		font.setBackgroundColor(Color.BLACK);
		font.drawShadowedText("FPS: " + fps + " UPDATES: " + ups, 0, 38, g);
		buffer.show();

		g.dispose();
	}
}