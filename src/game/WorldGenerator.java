package game;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class WorldGenerator {

	private Random generator;
	
	public WorldGenerator(int seed){
		
		generator = new Random(seed);
		
	}
	
	public BSPRect createSpaces(int meanRoomSize){

		return new BSPRect(0, 0, 640, 480, 64);
		
	}
}
