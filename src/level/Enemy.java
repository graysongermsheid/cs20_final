package level;

public abstract class Enemy extends Creature {
		
	protected int damage; //damage they deal
	protected String name;
	protected MovementPath path;

	protected enum MovementPath {

		LINE_X,
		LINE_Y,
		FOLLOW_PLAYER,
		DRUNK_WALK;

	}
}