package game.entities;

public abstract class LivingEntity extends Entity {
	
	protected float health;
	protected boolean alive;

	public LivingEntity(String spritesheet, int x, int y, float health, int speed){

		super(spritesheet, x, y, speed);
		this.health = health;
		alive = true;

	}

	public void takeDamage(float damage){

		health -= damage;
		roundHealth();

		if (health <= 0) {

			alive = false;

		}
	}

	private void roundHealth(){

		health = Math.round(health * 2) / 2;

	}

	public boolean isAlive(){

		return alive;

	}

	public float getHealth(){

		return health;

	}
}