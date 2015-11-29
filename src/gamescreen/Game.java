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
	int[][] tileWorld;
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
				
				switch (tileWorld[i][j]){

					case -1:
						g.setColor(Color.BLUE);
						break;

					case 0:
						g.setColor(new Color(0xE8, 0xD8, 0x83));
						break;

					case 1:
						g.setColor(new Color(0, 104, 10));
						break;

					case 2:
						g.setColor(new Color(119, 119, 119));
						break;

					case 3:
						g.setColor(new Color(153, 153, 153));
						break;

					case 4:
						g.setColor(new Color(200, 200, 200));
						break;
				}

				g.fillRect(j, i, 1, 1);
			}
		}

		g.setColor(new Color(255, 0, 255));
		for (int i = 0; i < 10; i++){

			for (int j = 0; j < 16; j++){

				g.drawRect(j * 64, i * 40, 64, 40);

			}
		}
	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED && !spaceHeld){

			world = w.generateWorld(1024, 640, 16, 0.6f);
			tileWorld = w.makeTileable(world);
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
		world = w.generateWorld(1024, 640, 16, 0.4f);
		tileWorld = w.makeTileable(world);
	}

	@Override
	public void unloadResources(){



	}
}