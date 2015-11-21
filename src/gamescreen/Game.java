package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;

import game.BSPNode;
import game.WorldGenerator;
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

		g.setColor(Color.YELLOW);

		for (int i = 0; i < cave.length; i++){

			for (int j = 0; j < cave[0].length; j++){

				if (cave[i][j]){

					g.fillRect(j * 8, i * 8, 8, 8);

				}
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
		
		CaveGenerator c = new CaveGenerator(48, 48);
		cave = c.generateMap(0, 1, 0.4f);

	}

	@Override
	public void unloadResources(){



	}

}