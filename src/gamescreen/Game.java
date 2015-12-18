package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import resources.ResourceManager;
import input.InputHandler;
import level.worldgen.CaveGenerator;
import gui.*;

import game.Camera;

public class Game implements GameScreen {
	
	///////////////////////////////////////////
	//Game has a seed -- save the seed and for each
	//z level seed the rng with (seed / (caveID * z-level))

	//that way the world has an ID, the each individual cave
	//system has an id, so all the game needs to save is 2 numbers
	
	private boolean paused;
	private boolean escHeld;
	private CaveGenerator c;
	private Camera cam;
	private Menu pauseMenu;

	public Game(){
		
		paused = false;
		escHeld = false;
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){
		
		if (paused){
			
			pauseMenu.update(elapsedMilliseconds);
			
		} else {
			
			cam.update(elapsedMilliseconds);
			
		}

	}

	@Override
	public void draw(Graphics2D g){
		
		cam.draw(g);
		
		if (paused){
			
			pauseMenu.draw(g);
			
		}
		
	}

	@Override
	public void processInput(){
		
		if (InputHandler.KEY_ESCAPE_PRESSED && !escHeld){
			
			escHeld = true;
			paused = !paused;
			
			if (paused){ pauseMenu.setVisible(true); }
			
			System.out.println("Paused opr whateer");
			
		} else if (!InputHandler.KEY_ESCAPE_PRESSED){
			
			escHeld = false;
			
		}
		
		if (!paused){

			cam.processInput();
			
		} else {
			
			pauseMenu.processInput();
			
		}
		
	}

	@Override
	public String getTitle(){

		return "Game";

	}

	@Override
	public void loadResources(){
		
		ResourceManager.createSpriteSheet("caves.png", 16, 16);
		ResourceManager.createSpriteSheet("sand.png", 16, 16);
		ResourceManager.createSpriteSheet("water.png", 16, 16);
		ResourceManager.createSpriteSheet("grass.png", 16, 16);
		ResourceManager.createSpriteSheet("mountain.png", 16, 16);
		ResourceManager.createSpriteSheet("mountain_high.png", 16, 16);
		ResourceManager.createSpriteSheet("font_bold.png", 16, 16);
		ResourceManager.createSpriteSheet("composite_four.png", 8, 8);
		ResourceManager.createSpriteSheet("composite_two.png", 16, 16);
		ResourceManager.createSpriteSheet("button.png", 24, 24);
		
		c = new CaveGenerator();
		cam = new Camera(0, 0, 128, 72, c.createLevel(64, 64, 4, 0.5f));

		pauseMenu = new Menu("composite_four.png", 480, 104, 320, 512);
		pauseMenu.addButton(new Button("EXIT", 32, 32));
	}

	@Override
	public void unloadResources(){

		ResourceManager.clearResources();

	}
}