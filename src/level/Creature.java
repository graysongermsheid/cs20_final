package level;

public class Creature extends Entity {
	
	protected int health;
	protected Point speed;
	protected boolean alive;

	public void takeDamage(int damage){

		health -= (damage > health) ? health : damage;
		alive = health > 0;

	}

	public void setHealth(int health){

		this.health = health;

	}

	public boolean isLiving(){

		return alive;

	}

	protected enum MovementState {

		IDLE_LEFT (0, 0),
		IDLE_RIGHT (1, 1),
		WALK_LEFT (2, 3),
		WALK_RIGHT (3, 4),
		ATTACK_LEFT (4, 5), 
		ATTACK_RIGHT (5, 6); //attacks used only for enemies (player can't attack)

		int[] values;

		MovementState(int... vals){

			values = vals;

		}
	}
}