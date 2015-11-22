package game;

public class MazeTile {
	
	protected boolean north = false;
	protected boolean south = false;
	protected boolean east = false;
	protected boolean west = false;
	protected boolean undiscovered = true;

	public MazeTile(){}

	public MazeTile(Direction accessFrom){

		switch (accessFrom){

			case NORTH:
				south = true;
				undiscovered = false;
				break;

			case SOUTH:
				north = true;
				undiscovered = false;
				break;

			case EAST:
				west = true;
				undiscovered = false;
				break;

			case WEST:
				east = true;
				undiscovered = false;
				break;

		}
	}

	private enum Direction {

		NORTH,
		SOUTH,
		EAST,
		WEST;

	}
}