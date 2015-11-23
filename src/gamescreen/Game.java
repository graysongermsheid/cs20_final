package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;

import game.BSPNode;
import game.CaveGenerator;

public class Game implements GameScreen {
	
	boolean[][] cave;
	
	public Game(){
		
		loadResources();
		
	}
	
	@Override
	public void update(double elapsedMilliseconds){



	}

	@Override
	public void draw(Graphics2D g){

		for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				if (!cave[i][j]){

					g.setColor(new Color(0, 0, 0));

				} else {

					g.setColor(new Color(255, 128, 0));

				}

				g.fillRect(j * 3, i * 3, 3, 3);

			}
		}
	}

	@Override
	public void processInput(){



	}

	@Override
	public String getTitle(){

		return "Yo! The Game.";

	}

	@Override
	public void loadResources(){
		
		CaveGenerator c = new CaveGenerator(128, 128);
		cave = c.generateMap(1, 0.4f);

	}

	@Override
	public void unloadResources(){



	}
}