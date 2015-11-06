package window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import input.InputHandler;
import game.ScreenManager;

public class GameCanvas extends Canvas{
	
	private BufferStrategy buffer;
	private ScreenManager screenManager;
	public static Dimension size;

	public void createBuffer(){

		this.createBufferStrategy(2);
		buffer = this.getBufferStrategy();

	}

	public void run(){

		size = getSize();

		System.out.println("Canvas size: " + size);

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

		screenManager = new ScreenManager();

		final int UPDATES_SECOND = 30;
		final int MAX_UPDATES_FRAME = 5;
		final int LOOP_SPEED = 1000000000 / UPDATES_SECOND;

		final int FRAMES_SECOND = 60;
		final int FRAME_SPEED = 1000000000 / FRAMES_SECOND;

		double currentTime = System.nanoTime();
		double lastUpdate = System.nanoTime();
		double lastRender = System.nanoTime();

		while (true){

			currentTime = System.nanoTime();
			int updates = 0;

			processInput();

			while (updates < MAX_UPDATES_FRAME && currentTime - lastUpdate > LOOP_SPEED){

				update((currentTime - lastUpdate) / 1000000);
				lastUpdate += LOOP_SPEED;
				updates++;

			}

			render();
			lastRender += FRAME_SPEED;

			while (currentTime - lastUpdate < LOOP_SPEED && currentTime - lastRender < FRAME_SPEED){

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

		g.setColor(new Color(108, 255, 100));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		screenManager.draw(g);

		buffer.show();

		g.dispose();
	}
}