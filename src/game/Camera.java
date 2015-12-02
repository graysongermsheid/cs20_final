package game;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import level.Level;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Camera {

	private Point location; //Pixels
	private Dimension size; //Pixels
	private Focus focus;
	
	private Level currentLevel;
	
	public void update(double elapsedMilliseconds){
		
		
		
	}
	
	public void processInput(){
		
		
		
	}
	
	public void draw(Graphics2D g){
		
		
		
	}
	
	protected enum Focus {
		
		//Whether the camera follows the player, locks to the center or locks to a corner
	
		PLAYER,
		CENTER,
		CORNER;
		
	}
}
