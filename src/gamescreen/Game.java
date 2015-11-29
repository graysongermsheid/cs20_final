package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import resources.ResourceManager;
import input.InputHandler;
import game.BSPNode;
import game.CaveGenerator.CaveNode;
import game.CaveGenerator;
import game.LevelReader;
import game.SimplexNoise;
import game.Level;
import game.WorldGenerator;

public class Game implements GameScreen {
	
	Random r;
	double[][] world;
	boolean spaceHeld = false;
	WorldGenerator w;

	public Game(){
		
		r = new Random();
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){



	}

	@Override
	public void draw(Graphics2D g){


		//l.draw(g);
		/*for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				g.setColor(Color.BLACK);
				g.fillRect(j * 10, i * 10, 10, 10);

				if (cave[i][j].empty){

					g.setColor(Color.WHITE);
					g.fillRect(j * 10, i * 10, 10, 10);

				}

			}
		}*/

		for (int i = 0; i < world.length; i++){

			for (int j = 0; j < world[0].length; j++){
				
				int rgb = (int) Math.pow(world[i][j], -1);
				g.setColor(new Color(rgb, rgb, rgb));
				g.fillRect(j, i, 1, 1);
			}
		}
	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED && !spaceHeld){

			world = w.generateWorld(1280, 720, 16, 0.6f);
			spaceHeld = true;

		} else if (!InputHandler.KEY_ACTION2_PRESSED){

			spaceHeld = false;

		}

	}

	@Override
	public String getTitle(){

		return "~ Voyageur ~";

	}

	@Override
	public void loadResources(){
		
		ResourceManager.createSpriteSheet("blowhard_forest_dark.png", 16, 16);
		ResourceManager.createSpriteSheet("font_bold.png", 16, 16);
		w = new WorldGenerator();
		world = w.generateWorld(1280, 720, 16, 0.4f);
	}

	@Override
	public void unloadResources(){



	}
}