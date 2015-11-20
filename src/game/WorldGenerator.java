package game;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class WorldGenerator {

	private Random generator;
	
	public WorldGenerator(int seed){
		
		generator = new Random(seed);
		
	}
	
	public BSPNode createSpaces(int meanRoomSize){

		return new BSPNode(0, 0, 64, 64);
		
	}
}
