package game;

import java.util.Random;

public class WorldGenerator {
	
	private Random r;

	public double[][] generateWorld(int width, int height, int initialPrecision, float landChance){

		r = new Random();
		OpenSimplexNoise os = new OpenSimplexNoise(r.nextLong());
		PerlinNoise p = new PerlinNoise(1);
		double[][] world = new double[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				//world[i][j] =  (p.noise(j, i) + 1) * 128;
				//world[i][j] += (p.noise(j / 24, i / 24) + 1) * 128;
				//world[i][j] += (p.noise(j / 12, i / 12) + 1) * 128;
				//world[i][j] += (p.noise(j / 6, i / 6) + 1) * 128;
				//world[i][j] += (p.noise(j / 3, i / 3) + 1) * 128;
				world[i][j] += (p.noise(j / 64.0, i / 64.0));

				//world[i][j] /= 5;
				//world[i][j] += 1;
			}
		}

		return world;

	}

	private boolean[][] enhancePrecision(int previousLevel, int level, int minLevel, boolean[][] array){

		boolean[][] previousArray = array.clone();

		for (int i = 0; i < array.length / level; i++){

			for (int j = 0; j < array[0].length / level; j++){

				for (int k = 0; k < level; k++){

					for (int e = 0; e < level; e++){

						boolean[] neighbours = getNeighbours(j * level + e, i * level + k, previousLevel, previousArray);
						float landChance = (array[i * level][j * level]) ? 0.1f : 0f;

						landChance += neighbours[1] ? 0.225f : 0;
						landChance += neighbours[3] ? 0.225f : 0;
						landChance += neighbours[5] ? 0.225f : 0;
						landChance += neighbours[7] ? 0.225f : 0;

						array[i * level + k][j * level + e] = (r.nextFloat() < landChance);

					}
				}
			}
		}

		if (level == minLevel){

			return array;

		} else {

			return enhancePrecision(level, level / 2, minLevel, array);

		}
	}

	private boolean[] getNeighbours(int x, int y, int precision, boolean[][] map){

		x = (int)Math.floor((double)x / (double)precision);
		y = (int)Math.floor((double)y / (double)precision);
		boolean[] neighbours = new boolean[8];

		neighbours [0] = (x > 0 && y > 0)  ? map[(y - 1) * precision][(x - 1) * precision] : false;
		neighbours [1] = (y > 0)  ? map[(y - 1) * precision][x * precision] : false;
		neighbours [2] = (x < (map[0].length / precision) - 1 && y > 0)  ? map[(y - 1) * precision][(x + 1) * precision] : false;
		neighbours [3] = (x > 0)  ? map[y * precision][(x - 1) * precision] : false;
		neighbours [4] = (x < (map[0].length / precision) - 1) ? map[y * precision][(x + 1) * precision] : false;
		neighbours [5] = (x > 0 && y < (map.length / precision) - 1)  ? map[(y + 1) * precision][(x - 1) * precision] : false;
		neighbours [6] = (y < (map.length / precision) - 1)  ? map[(y + 1) * precision][x * precision] : false;
		neighbours [7] = (x < (map[0].length / precision) - 1 && y < (map.length / precision) - 1)  ? map[(y + 1) * precision][(x + 1) * precision] : false;

		return neighbours;

	}
}