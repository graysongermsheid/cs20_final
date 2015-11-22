package game;

import java.util.Random;

public class MazeGenerator {
	
	private MazeTile[][] maze;

	public MazeGenerator(int width, int height){

		maze = new MazeTile[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				maze[i][j] = new MazeTile();

			}
		}
	}

	private void expandMaze(int x, int y, MazeTile.Direction relativePosition){

	

	}
}