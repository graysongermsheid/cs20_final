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

import game.Camera;

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
	private CaveGenerator c;
	private WorldGenerator w;
	private Camera cam;

	public Game(){
		
		r = new Random();
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){

		cam.update(elapsedMilliseconds);

	}

	@Override
	public void draw(Graphics2D g){


		//l.draw(g);
		/*for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				if (cave[i][j].empty){

					g.setColor(new Color(cave[i][j].tag * 750000));
					g.fillRect(j, i, 1, 1);

				}
			}
		}*/

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

				g.fillRect(j * 2, i * 2, 2, 2);
			}
		}*/

		/*g.setColor(new Color(255, 0, 255));
		for (int i = 0; i < 16; i++){

			for (int j = 0; j < 16; j++){

				g.drawRect(j * 64, i * 40, 64, 40);

			}
		}*/
		
		cam.draw(g);
	}

	@Override
	public void processInput(){

		if (InputHandler.KEY_ACTION2_PRESSED && !spaceHeld){

			cave = c.generateMap(1280,  720,  4,  0.5f);
			cam.setLevel(0, 0, c.createLevel(64, 64, 0, 0.5f));
			world = w.generateWorld(64, 64, 0, 0);
			spaceHeld = true;

		} else if (!InputHandler.KEY_ACTION2_PRESSED){

			spaceHeld = false;

		}

		cam.processInput();
	}

	@Override
	public String getTitle(){

		return "Game";

	}

	@Override
	public void loadResources(){
		
		ResourceManager.createSpriteSheet("Tolos.png", 16, 16);
		ResourceManager.createSpriteSheet("font_bold.png", 16, 16);
		c = new CaveGenerator();
		w = new WorldGenerator();
		world = w.generateWorld(640, 360, 0, 0);
		cave = c.generateMap(1280, 720, 3, 0.5f);
		cam = new Camera(0, 0, 320, 180, c.createLevel(1280, 720, 3, 0.5f));
	}

	@Override
	public void unloadResources(){



	}
}