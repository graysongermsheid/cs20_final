package game;

public class Player extends Creature {
	
	private final double ATTACK_COOLDOWN = 1500.0;
	private double cooldownTimer;

	public Player(String savefile){



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

		switch (c){

			case CollisionType.NONE:

				return;

			case CollisionType.WALL:

				return;

			case CollisionType.DOOR:

				return;

		}

	}

	private void load(String savefile){

		//load existing player (location, items, hp, etc...)

	}
}