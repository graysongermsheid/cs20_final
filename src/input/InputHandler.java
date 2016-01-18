package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.Point;
import input.KeyBindings;

public class InputHandler extends MouseInputAdapter implements MouseWheelListener, KeyListener{
	
	public static Point MOUSE_CLICK_LOCATION;
	public static Point MOUSE_LOCATION;

	public static boolean MOUSE_CLICKED;
	public static boolean MOUSE_DRAGGED;

	public static boolean ANY_KEY_PRESSED;

	public static boolean KEY_UP_PRESSED;
	public static boolean KEY_DOWN_PRESSED;
	public static boolean KEY_LEFT_PRESSED;
	public static boolean KEY_RIGHT_PRESSED;
	public static boolean KEY_ACTION_PRESSED;
	public static boolean KEY_ACTION2_PRESSED;
	public static boolean KEY_ESCAPE_PRESSED;
	public static boolean KEY_DEBUG_PRESSED;

	public static boolean KEY_UP_RELEASED;
	public static boolean KEY_DOWN_RELEASED;
	public static boolean KEY_LEFT_RELEASED;
	public static boolean KEY_RIGHT_RELEASED;
	public static boolean KEY_ACTION_RELEASED;
	public static boolean KEY_ACTION2_RELEASED;
	public static boolean KEY_ESCAPE_RELEASED;


	@Override
	public void keyPressed(KeyEvent e){

		ANY_KEY_PRESSED = true;

		if (KeyBindings.UP.matches(e.getKeyCode())){

			KEY_UP_PRESSED = true;
			KEY_UP_RELEASED = false;

		} else if (KeyBindings.DOWN.matches(e.getKeyCode())){

			KEY_DOWN_PRESSED = true;
			KEY_DOWN_RELEASED = false;

		} else if (KeyBindings.LEFT.matches(e.getKeyCode())){

			KEY_LEFT_PRESSED = true;
			KEY_LEFT_RELEASED = false;
			
		} else if (KeyBindings.RIGHT.matches(e.getKeyCode())){

			KEY_RIGHT_PRESSED = true;
			KEY_RIGHT_RELEASED = false;
			
		} else if (KeyBindings.ACTION.matches(e.getKeyCode())){

			KEY_ACTION_PRESSED = true;
			KEY_ACTION_RELEASED = false;
			
		} else if (KeyBindings.ACTION2.matches(e.getKeyCode())){

			KEY_ACTION2_PRESSED = true;
			KEY_ACTION2_RELEASED = false;
			
		} else if (KeyBindings.ESCAPE.matches(e.getKeyCode())){
			
			KEY_ESCAPE_PRESSED = true;
			KEY_ESCAPE_RELEASED = false;
			
		} else if (KeyBindings.DEBUG.matches(e.getKeyCode())){
			
			KEY_DEBUG_PRESSED = true;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e){

		ANY_KEY_PRESSED = false;

		if (KeyBindings.UP.matches(e.getKeyCode())){

			KEY_UP_PRESSED = false;
			KEY_UP_RELEASED = true;

		} else if (KeyBindings.DOWN.matches(e.getKeyCode())){

			KEY_DOWN_PRESSED = false;
			KEY_DOWN_RELEASED = true;

		} else if (KeyBindings.LEFT.matches(e.getKeyCode())){

			KEY_LEFT_PRESSED = false;
			KEY_LEFT_RELEASED = true;
			
		} else if (KeyBindings.RIGHT.matches(e.getKeyCode())){

			KEY_RIGHT_PRESSED = false;
			KEY_RIGHT_RELEASED = true;
			
		} else if (KeyBindings.ACTION.matches(e.getKeyCode())){

			KEY_ACTION_PRESSED = false;
			KEY_ACTION_RELEASED = true;
			
		} else if (KeyBindings.ACTION2.matches(e.getKeyCode())){

			KEY_ACTION2_PRESSED = false;
			KEY_ACTION2_RELEASED = true;
			
		} else if (KeyBindings.ESCAPE.matches(e.getKeyCode())){
			
			KEY_ESCAPE_PRESSED = false;
			KEY_ESCAPE_RELEASED = true;
			
		} else if (KeyBindings.DEBUG.matches(e.getKeyCode())){
			
			KEY_DEBUG_PRESSED = false;
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e){

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e){

	}

	@Override
	public void mousePressed(MouseEvent e){

		MOUSE_CLICKED = true;
		MOUSE_CLICK_LOCATION = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e){

		MOUSE_CLICK_LOCATION = null;
		MOUSE_CLICKED = false;
		MOUSE_DRAGGED = false;

	}

	@Override
	public void mouseClicked(MouseEvent e){

	}

	@Override
	public void mouseMoved(MouseEvent e){

		MOUSE_LOCATION = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e){

		MOUSE_DRAGGED = true;
		MOUSE_CLICKED = true;
		MOUSE_LOCATION = e.getPoint();
	}

	public static void resetKeys(){

		KEY_UP_RELEASED = false;
		KEY_DOWN_RELEASED = false;
		KEY_LEFT_RELEASED = false;
		KEY_RIGHT_RELEASED = false;
		KEY_ACTION_RELEASED = false;
		KEY_ACTION2_RELEASED = false;
		KEY_ESCAPE_RELEASED = false;
	}
}