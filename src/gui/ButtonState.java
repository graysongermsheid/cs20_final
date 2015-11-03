package gui;

public enum ButtonState {
	
	DOWN (0),
	DEFAULT (1),
	MOUSED_OVER (2);

	int stateValue;

	ButtonState(int stateValue){

		this.stateValue = stateValue;

	}

	public int value(){

		return stateValue;

	}
}