package input;

import java.awt.event.KeyEvent;

public enum KeyBindings{
	
	UP (KeyEvent.VK_W, KeyEvent.VK_UP),
	DOWN (KeyEvent.VK_S, KeyEvent.VK_DOWN),
	LEFT (KeyEvent.VK_A, KeyEvent.VK_LEFT),
	RIGHT (KeyEvent.VK_D, KeyEvent.VK_RIGHT),
	ACTION (KeyEvent.VK_ENTER, KeyEvent.VK_X),
	ACTION2 (KeyEvent.VK_SPACE, KeyEvent.VK_Z);

	int[] bindings;

	KeyBindings(int... bindings){

		this.bindings = bindings;

	}

	public boolean matches(int value) {

		for (int i = 0; i < bindings.length; i++){

				if (value == bindings[i]){

					return true;

				}
		}

		return false;
	}

	public int binding(int num){

		if (num < bindings.length){

			return bindings[num];

		} else {

			return -1;

		}
	}
}