package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import level.AABB;
import level.Level;
import level.worldgen.*;
import resources.ResourceManager;

public class Door extends Entity {

	private AreaType destination;
	private Random r;
	private AreaType currentArea;
	private static DungeonGenerator d = new DungeonGenerator();
	private static CaveGenerator c = new CaveGenerator();
	protected static Camera camRef; //pointer to camera so this can switch levels
	
	public Door(int x, int y){
		
		super(x, y, 16, 16, "ladder.png");
		currentArea = camRef.getAreaType();
		
		r = new Random();
		int a = r.nextInt(5);
		
		if (a == 0){
			
			destination = AreaType.DUNGEONS;
			
		} else {
			
			destination = AreaType.CAVES;
			
		}
		
		if (currentArea == AreaType.DUNGEONS){
			
			hitBox = new AABB(x + 5, y + 5, 13, 13);
			
		} else {

			hitBox = new AABB(x + 3, y + 4, 16, 16);
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g, Point p) {

		g.drawImage(sourceSprites.getImage(currentArea.getValue()), position.x - p.x, position.y - p.y, null);
		
	}

	@Override
	protected void collide(Entity e) {

		if (e.getType() == EntityType.PLAYER){
			
			ResourceManager.playSound("fall.wav");
			
			Level newLevel;
			
			if (destination == AreaType.CAVES){

				camRef.setLevel(0, 0, c.createLevel(r.nextInt(33) + 32, r.nextInt(33) + 32, r.nextInt(3) + 2, 0.5f, 40.0));
				
			} else {
				
				camRef.setLevel(0, 0, d.createLevel(r.nextInt(4) + 2, r.nextInt(4) + 2));
				
			}
			
		}
		
	}
	
	@Override
	public EntityType getType(){
		
		return EntityType.DOOR;
		
	}
}
