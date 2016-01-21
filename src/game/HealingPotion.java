package game;

import java.awt.Graphics2D;
import java.awt.Point;

import level.AABB;
import resources.ResourceManager;

public class HealingPotion extends Entity {

	private int amount;
	private int image;
	
	public HealingPotion(int x, int y, int amount){
		
		super (x, y, 16, 16, "health.png");
		
		if (amount == 25){
		
			this.amount = 30;
			hitBox = new AABB(x + 3, y + 2, 9, 10);
			image = 3;
			
		} else if (amount >= 20){
			
			this.amount = 25;
			hitBox = new AABB(x + 5, y + 2, 5, 10);
			image = 2;
			
		} else if (amount >= 15) {
				
			this.amount = 15;
			hitBox = new AABB(x + 1, y + 2, 13, 10);
			image = 1;

		} else {
			
			this.amount = 10;
			hitBox = new AABB(x + 3, y + 1, 8, 10);
			image = 0;
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {}

	@Override
	public void draw(Graphics2D g, Point p) {

		g.drawImage(sourceSprites.getImage(image), position.x - p.x, position.y - p.y, null);
		
	}

	@Override
	protected void collide(Entity e) {

		if (e.getType() == EntityType.PLAYER && !disposalFlag){
			
			Player p = (Player)e;
			
			if (p.health < Player.MAX_HEALTH){
			
					disposalFlag = true;
					ScoreTracker.healthHealed += amount;
					p.health = Math.min(p.health + amount, Player.MAX_HEALTH);
					
					ResourceManager.playSound("heal.wav");
					
					if (p.health == Player.MAX_HEALTH){
						
						ResourceManager.playSound("heal_full.wav");
						
					}
					
			}
		}

	}
	
	public int getHealAmount(){
		
		return amount;
		
	}
	
	@Override
	public EntityType getType(){
		
		return EntityType.HEAL;
		
	}
}
