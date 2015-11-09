package game.entities;

public enum AnimationState {
	
	WALK_UP (3),
	WALK_DOWN (0),
	WALK_LEFT (2), 
	WALK_RIGHT (1);

	int animation;

	AnimationState(int value){

		animation = value;

	}

	public int value(){

		return animation;

	}
}