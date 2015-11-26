package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;

import resources.ResourceManager;
import game.BSPNode;
import game.CaveGenerator.CaveNode;
import game.CaveGenerator;
import game.LevelReader;
import game.SimplexNoise;
import game.Level;

public class Game implements GameScreen {
	
	CaveNode[][] cave;
	BSPNode bsp;
	int[][] noise;
	Level l;
	
	public Game(){
		
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){



	}

	@Override
	public void draw(Graphics2D g){

		/*for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				if (cave[i][j].empty){

					g.setColor(new Color(cave[i][j].tag * 750000));

				} else {

					g.setColor(new Color(10, 10, 10));

				}

				g.fillRect(j * 10, i * 10, 10, 10);

			}
		}*/

		l.draw(g);
	}

	@Override
	public void processInput(){



	}

	@Override
	public String getTitle(){

		return "~ Voyageur ~";

	}

	@Override
	public void loadResources(){
		
		ResourceManager.createSpriteSheet("default.png", 32, 32);

		CaveGenerator c = new CaveGenerator(0);
		cave = c.generateMap(64, 48, 4, 0.5f);
		
		bsp = new BSPNode(0, 0, 64, 64);
		noise = SimplexNoise.generateArray(128, 96, 0, 0, 3);

		l = LevelReader.loadLevel("butts");
	}

	@Override
	public void unloadResources(){



	}
}