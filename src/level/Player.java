package level;

public class Player extends Creature {
	
	private final double ATTACK_COOLDOWN = 1500.0;
	private static double cooldownTimer;
	
	public Player(String savefile){

		//Maybe load a save file... who knows?

	}
	
	public Player(int x, int y){
		
		this.bounds = new AABB(x, y, 16, 16);
		
	}

	public void processInput(){



	}

	@Override
	public void update(double elapsedMilliseconds){

		super.update(elapsedMilliseconds);

	}

	@Override
	public void processCollision(Entity e){



	}

	@Override
	public void processCollision(int c){

		switch (CollisionType.values()[c]){

			case NONE:

				return;

			case WALL:

				return;

			case DOOR:

				return;

		}

	}

	private void load(String savefile){

		//load existing player (location, items, hp, etc...)

	}
}