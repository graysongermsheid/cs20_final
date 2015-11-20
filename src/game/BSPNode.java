package game;

import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class BSPNode {
	
	private static Random random = new Random();
	private static int minX = 16;
	private static int minY = 12;

	private int width;
	private int height;
	private int x;
	private int y;

	private BSPNode right;
	private BSPNode left;

	private Color c = Color.GREEN;

	private SplitDirection direction;
	protected boolean tooSmall;

	public BSPNode(int x, int y, int width, int height){

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		split();

		if (width < 8 || height < 8){

			tooSmall = true;
			c = Color.RED;

		}
	}

	public void draw(Graphics2D g){

		if (right == null && left == null){

			g.setColor(c);
			g.draw(new Rectangle((int)(x * 7), (int)(y * 7), (int)(width * 7), (int)(height * 7)));

		} else {

			if (left != null){

				left.draw(g);

			}

			if (right != null){

				right.draw(g);

			}
		}
	}

	private void split(){

		if ((right != null || left != null)){

			direction = null;
			return;

		}

		if ((height >= width * 1.25) && (height >= minY * 1.25)){

			direction = SplitDirection.VERTICAL;

		} else if ((width >= height * 1.25) && (width >= minX * 1.25)){

			direction = SplitDirection.HORIZONTAL;

		} else if (!canSplit()){

			return;

		} else {

			direction = (random.nextInt(1) == 1) ? SplitDirection.HORIZONTAL : SplitDirection.VERTICAL;

		}

		int splitPoint;
		int attempts = 0;

		switch (direction){

			case VERTICAL:

				do {

					splitPoint = random.nextInt(height / 2) + 10;
					attempts++;

				} while ((attempts < 40) && (y + splitPoint > height) || (height - splitPoint < 10));

				left = new BSPNode(x, y, width, splitPoint);
				right = new BSPNode(x, y + splitPoint, width, height - splitPoint);
				break;

			case HORIZONTAL:

				do {

					splitPoint = random.nextInt(width / 2) + 10;
					attempts++;
				
				} while ((attempts < 40) && ((x + splitPoint > width) || (width - splitPoint) < 10));

				left = new BSPNode(x, y, splitPoint, height);
				right = new BSPNode(x + splitPoint, y, width - splitPoint, height);
				break;

		}

		//if (left.tooSmall) {left = null;}
		//if (right.tooSmall) {right = null;}
	}

	private boolean canSplit(){

		if (width > minX * 1.25 ||
			height > minY * 1.25){

			return true;

		}

		return false;

	}

	private enum SplitDirection {

		HORIZONTAL,
		VERTICAL;

	}
}