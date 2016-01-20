package level.worldgen;

import level.CollisionType;
import level.Tile;

public class LevelPiece {

	public final Tile t;
	public final CollisionType c;
	
	public LevelPiece(Tile t, CollisionType c){
		
		this.t = t;
		this.c = c;
		
	}
}
