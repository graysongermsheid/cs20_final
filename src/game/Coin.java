package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import level.AABB;

public class Coin extends Entity {

	private int value;
	
	public Coin(int x, int y, int value){
		
		super(x, y, 16, 16, "coins.png");
		this.value = value;
		
		if (value <= 5){
			
			hitBox = new AABB(x + 5, y + 5, 5, 5);
			
		} else if (value <= 10){
			
			hitBox = new AABB(x + 2, y + 2, 12, 12);
			
		} else if (value <= 20){
			
			hitBox = new AABB(x, y, 15, 15);
			
		} else {
			
			hitBox = new AABB(x + 2, y + 3, 11, 9);
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g, Point p) {

		if (value <= 5){
			
			g.drawImage(sourceSprites.getImage(0), position.x - p.x, position.y - p.y, null);
			
		} else if (value <= 10){
			
			g.drawImage(sourceSprites.getImage(1), position.x - p.x, position.y - p.y, null);
			
		} else if (value <= 20){
			
			g.drawImage(sourceSprites.getImage(2), position.x - p.x, position.y - p.y, null);
			
		} else {
			
			g.drawImage(sourceSprites.getImage(3), position.x - p.x, position.y - p.y, null);
			
		}
		
	}

	@Override
	protected void collide(Entity e) {

		if (e.getType() == EntityType.PLAYER && !disposalFlag){
			
			ScoreTracker.goldCollected += value;
			ScoreTracker.score += value * 10;
			
			Random r = new Random();
			int num = r.nextInt(3);
			resources.ResourceManager.playSound("coin" + num + ".wav");
			
			disposalFlag = true;
			
		}
	}

	public int getVal(){
		
		return value;
		
	}

	@Override
	public EntityType getType(){
		
		return EntityType.COIN;
		
	}
}
