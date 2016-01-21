package gamescreen;

import java.awt.Graphics2D;

import resources.ResourceManager;
import input.InputHandler;
import level.worldgen.CaveGenerator;
import level.worldgen.DungeonGenerator;
import gui.*;

import game.Camera;

public class Game implements GameScreen {
	
	private boolean paused;
	private boolean escHeld;
	private CaveGenerator c;
	private Camera cam;
	private Menu pauseMenu;
	private DungeonGenerator d;

	public Game(){
		
		paused = false;
		escHeld = false;
		//loadResources();
		
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
		
		d = new DungeonGenerator();
		
		ResourceManager.createSpriteSheet("caves.png", 16, 16);
		ResourceManager.createSpriteSheet("dungeon.png", 16, 16);
		ResourceManager.createSpriteSheet("font.png", 16, 16);
		ResourceManager.createSpriteSheet("font_small.png", 4, 6);
		ResourceManager.createSpriteSheet("composite_two.png", 16, 16);
		ResourceManager.createSpriteSheet("button.png", 24, 24);
		ResourceManager.createSpriteSheet("player.png", 16, 16);
		ResourceManager.createSpriteSheet("snake.png", 16, 16);
		ResourceManager.createSpriteSheet("guard.png", 16, 16);
		ResourceManager.createSpriteSheet("ladder.png", 16, 16);
		ResourceManager.createSpriteSheet("coins.png", 16, 16);
		ResourceManager.createSpriteSheet("health.png", 16, 16);
		
		cam = new Camera(0, 0, 256, 144, d.createLevel(5, 5));
		
		pauseMenu = new Menu("composite_two.png", 480, 104, 320, 200);
		pauseMenu.lock();
		pauseMenu.addButton(new Button("X", 304, -16, 32, 32){
			
			@Override
			public void performAction(){
				
				paused = false;
				pauseMenu.setVisible(false);
				
			}
			
		});
		pauseMenu.addButton(new Button("Mute", 12, 12, 296, 32){
			
			@Override
			public void performAction(){
				
				ResourceManager.MUTE = !ResourceManager.MUTE;
				
			}
			
		});
		pauseMenu.addButton(new Button("Menu", 12, 56, 296, 32){
	
			@Override
			public void performAction(){
		
				ScreenManager.switchCurrentScreen(new MainMenu());
		
			}
	
		});
		pauseMenu.addButton(new Button("Give Up", 12, 100, 296, 32){
			
			@Override
			public void performAction(){
				
				ScreenManager.switchCurrentScreen(new GameOver("Boredom"));
				
			}
			
		});
		pauseMenu.addButton(new Button("Quit Game", 12, 144, 296, 32){
			
			@Override
			public void performAction(){
				
				System.exit(0);
				
			}
			
		});
	}

	@Override
	public void unloadResources(){

		ResourceManager.clearResources();

	}
}