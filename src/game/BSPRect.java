package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class BSPRect extends Rectangle{

	private boolean empty;
	private static int minSize;
	public static ArrayList<BSPRect> subRectangles = new ArrayList<BSPRect>();
	private static Random r = new Random();
	private Color c;
	
	public BSPRect(int x, int y, int width, int height, int minSize){
		
		super(x, y, width, height);
		empty = true;
		BSPRect.minSize = minSize;
		c = new Color(255, 0, 255);
		split();
		
	}
	
	public BSPRect(int x, int y, int width, int height){
		
		super(x, y, width, height);
		empty = true;
		c = new Color(255, 0, 255);
		BSPRect[] b = split();
		
		if (b != null){
			
			subRectangles.add(b[0]);
			subRectangles.add(b[1]);
			
		}
	}
	
	public void draw(Graphics2D g){
		
		g.draw(this);
		
	}
	
	public BSPRect[] split(){
		
		BSPRect[] subRects = null;
		
		if((this.width <= minSize && this.height <= minSize) || !empty){
			
			empty = true;
			return subRects;
			
		}

		subRects = new BSPRect[2];
		
		if (this.width <= minSize && this.height > minSize){
			
			int splitPoint = Math.abs((int)((r.nextGaussian() * 3) + (this.height / 2)));
			BSPRect sub1 = new BSPRect(x, y, width, splitPoint);
			BSPRect sub2 = new BSPRect(x, y + splitPoint, width, height - splitPoint);
			
			subRects[0] = sub1;
			subRects[1] = sub2;
			
			empty = false;
			
			return subRects;
			
		} else if (this.height <= minSize && this.width > minSize){
			
			int splitPoint = Math.abs((int)((r.nextGaussian() * 3) + (this.height / 2)));
			BSPRect sub1 = new BSPRect(x, y, splitPoint, height);
			BSPRect sub2 = new BSPRect(x + splitPoint, y, width - splitPoint, height);
			
			subRects[0] = sub1;
			subRects[1] = sub2;
			
			empty = false;
			
			return subRects;
			
		} else {
			
			if (r.nextFloat() > 0.5f){
				
				int splitPoint = Math.abs((int)((r.nextGaussian() * 3) + (this.height / 2)));
				BSPRect sub1 = new BSPRect(x, y, width, splitPoint);
				BSPRect sub2 = new BSPRect(x, y + splitPoint, width, height - splitPoint);
				
				subRects[0] = sub1;
				subRects[1] = sub2;
				
				empty = false;
				
				return subRects;
				
			} else {
				
				int splitPoint = Math.abs((int)((r.nextGaussian() * 3) + (this.height / 2)));
				BSPRect sub1 = new BSPRect(x, y, splitPoint, height);
				BSPRect sub2 = new BSPRect(x + splitPoint, y, width - splitPoint, height);
				
				subRects[0] = sub1;
				subRects[1] = sub2;
				
				empty = false;
				
				return subRects;
				
			}
		}
	}
}
