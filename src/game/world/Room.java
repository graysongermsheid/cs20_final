package game.world;

import java.awt.Graphics2D;
import game.entities.*;

public class Room implements Level {
	
	private TileLayer[] tileLayers;
	private Entity[] entities;

	public void load(){



	}

	public void unload(){



	}

	public void processInput(){



	}

	public void update(double elapsedMilliseconds){

		for (TileLayer t : tileLayers){

			t.update(elapsedMilliseconds);

		}

		for (Entity e : entities){

			e.update(elapsedMilliseconds);

		}
	}

	public void draw(Graphics2D g){

		for (TileLayer t : tileLayers){

			t.draw(g);

		}

		for (Entity e : entities){

			e.draw(g);

		}
	}
}