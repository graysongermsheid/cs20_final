package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import level.AABB;
import level.Level;
import level.worldgen.*;
import resources.ResourceManager;

public class Door extends Entity {

	private DoorDestination destination;
	private Random r;
	private static DungeonGenerator d = new DungeonGenerator();
	private static CaveGenerator c = new CaveGenerator();
	public static Camera camRef; //pointer to camera so this can switch levels
	
	public Door(int x, int y){
		
		super(x, y, 16, 16, "ladder.png");
		hitBox = new AABB(x + 1, y + 1, 13, 13);
		
		r = new Random();
		int a = r.nextInt(5);
		
		if (a == 0){
			
			destination = DoorDestination.DUNGEONS;
			
		} else {
			
			destination = DoorDestination.CAVES;
			
		}
		
	}
	
	@Override
	public void update(double elapsedMilliseconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g, Point p) {

		g.drawImage(sourceSprites.getImage(0), position.x - p.x, position.y - p.y, null);
		
	}

	@Override
	protected void collide(Entity e) {

		if (e.getType() == EntityType.PLAYER){
			
			ResourceManager.playSound("fall.wav");
			
			Level newLevel;
			
			if (destination == DoorDestination.CAVES){

				camRef.setLevel(0, 0, c.createLevel(r.nextInt(33) + 32, r.nextInt(33) + 32, r.nextInt(3) + 2, 0.5f));
				
			} else {
				
				camRef.setLevel(0, 0, d.createLevel(r.nextInt(4) + 2, r.nextInt(4) + 2));
				
			}
			
		}
		
	}

	private enum DoorDestination{
		
		DUNGEONS ("Dungeons"),
		CAVES ("Caves");
		
		String name;
		
		DoorDestination(String val){
			
			name = val;
			
		}
		
		@Override
		public String toString(){
			
			return name;
			
		}
	}
	
	@Override
	public EntityType getType(){
		
		return EntityType.DOOR;
		
	}
}
