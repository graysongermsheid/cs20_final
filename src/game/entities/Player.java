package game.entities;

import java.awt.Graphics2D;
import input.InputHandler;
import java.awt.Point;

public final class Player extends LivingEntity {
	
	public Player(int x, int y){

		super("Player.png", x, y, 5, 2);

	}

	@Override
	public void update(double elapsedMilliseconds){

		Point oldLocation = new Point(location.x, location.y);

		location.x += velocity.x;
		location.y += velocity.y;

		if (location.equals(oldLocation)){

			animations[currentAnimation.value()].setFrame(0);

		} else {

			animations[currentAnimation.value()].update(elapsedMilliseconds);

		}

	}

	@Override
	public void draw(Graphics2D g){

		g.drawImage(animations[currentAnimation.value()].currentFrame(), location.x, location.y, null);

	}

	public void processInput(){

		if (InputHandler.KEY_UP_PRESSED && !InputHandler.KEY_DOWN_PRESSED){

			velocity.setLocation(velocity.x, -1 * speed);
			currentAnimation = AnimationState.WALK_UP;

		} else if (InputHandler.KEY_DOWN_PRESSED && !InputHandler.KEY_UP_PRESSED){

			velocity.setLocation(velocity.x, speed);
			currentAnimation = AnimationState.WALK_DOWN;

		} else {

			velocity.setLocation(velocity.x, 0);

		}

		if (InputHandler.KEY_LEFT_PRESSED && !InputHandler.KEY_RIGHT_PRESSED){

			velocity.setLocation(-1 * speed, velocity.y);
			currentAnimation = AnimationState.WALK_LEFT;

		} else if (InputHandler.KEY_RIGHT_PRESSED && !InputHandler.KEY_LEFT_PRESSED){

			velocity.setLocation(speed, velocity.y);
			currentAnimation = AnimationState.WALK_RIGHT;

		} else {

			velocity.setLocation(0, velocity.y);

		}
	}
}