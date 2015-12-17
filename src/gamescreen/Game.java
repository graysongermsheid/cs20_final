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
		pauseMenu = new Menu("composite_four.png", 512, 104, 256, 512);
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){

		cam.update(elapsedMilliseconds);

	}

	@Override
	public void draw(Graphics2D g){
		
		cam.draw(g);
	}

	@Override
	public void processInput(){
		
		if (InputHandler.KEY_ESCAPE_PRESSED && !escHeld){
			
			escHeld = true;
			paused = !paused;
			System.out.println("Paused opr whateer");
			
		} else if (escHeld){
			
			escHeld = false;
			
		}
		
		if (!paused){

			cam.processInput();
			
		} else {
			
			pauseMenu.setVisible(true);
			
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
		c = new CaveGenerator();
		cam = new Camera(0, 0, 128, 72, c.createLevel(64, 64, 4, 0.5f));
	}

	@Override
	public void unloadResources(){

		ResourceManager.clearResources();

	}
}