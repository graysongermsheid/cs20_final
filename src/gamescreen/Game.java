package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import resources.ResourceManager;
import input.InputHandler;
import level.worldgen.BSPNode;
import level.worldgen.CaveGenerator.CaveNode;
import level.worldgen.CaveGenerator;
import level.Level;
import level.worldgen.WorldGenerator;

public class Game implements GameScreen {
	
	///////////////////////////////////////////
	//Game has a seed -- save the seed and for each
	//z level seed the rng with (seed / (caveID * z-level))

	//that way the world has an ID, the each individual cave
	//system has an id, so all the game needs to save is 2 numbers
	
	
	private Random r;
	private int[][] world;
	private int[][] tileWorld;
	private CaveNode[][] cave;
	private boolean spaceHeld = false;
	private WorldGenerator w;
	private CaveGenerator c;

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
		g.setColor(Color.RED);
		for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				if (cave[i][j].empty){

					g.fillRect(j * 10 + 320, i * 10, 10, 10);

				}
			}
		}

		/*for (int i = 0; i < world.length; i++){

			for (int j = 0; j < world[0].length; j++){
				
				//int x = (int)(world[i][j] * 255);
				//g.setColor(new Color(x, x, x));

				switch (world[i][j]){

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
		}*/

		/*g.setColor(new Color(255, 0, 255));
		for (int i = 0; i < 16; i++){

			for (int j = 0; j < 16; j++){

				g.drawRect(j * 64, i * 40, 64, 40);

			}
		}/*/
	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED && !spaceHeld){

			world = w.generateWorld(12, 640, 16, 0.6f);
			cave = c.generateMap(64, 64, 3, 0.5f);
			spaceHeld = true;

		} else if (!InputHandler.KEY_ACTION2_PRESSED){

			spaceHeld = false;

		}

	}

	@Override
	public String getTitle(){

		return "Game";

	}

	@Override
	public void loadResources(){
		
		ResourceManager.createSpriteSheet("blowhard_forest_dark.png", 16, 16);
		ResourceManager.createSpriteSheet("font_bold.png", 16, 16);
		c = new CaveGenerator(123);
		cave = c.generateMap(64, 64, 3, 0.5f);
		w = new WorldGenerator();
		world = w.generateWorld(128, 64, 16, 0.4f);
	}

	@Override
	public void unloadResources(){



	}
}