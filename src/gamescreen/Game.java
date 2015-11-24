package gamescreen;

import java.awt.Color;
import java.awt.Graphics2D;

import game.BSPNode;
import game.CaveGenerator;

public class Game implements GameScreen {
	
	boolean[][] cave;
	BSPNode bsp;
	
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

				g.fillRect(j * 10, i * 10, 10, 10);

			}
		}
		
		//bsp.draw(g);
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
		
		CaveGenerator c = new CaveGenerator(64, 48);
		cave = c.generateMap(4, 0.5f);
		
		bsp = new BSPNode(0, 0, 64, 64);

	}

	@Override
	public void unloadResources(){



	}
}