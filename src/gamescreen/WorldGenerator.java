package game;

import java.util.Random;

public class WorldGenerator {
	
	private Random r;

	public double[][] generateWorld(int width, int height, int initialPrecision, float landChance){

		r = new Random();
		OpenSimplexNoise os = new OpenSimplexNoise(r.nextLong());
		PerlinNoise p = new PerlinNoise();
		double[][] world = new double[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				world[i][j] += p.noise(j / 4.0, i / 4.0) * 0.0625;
				world[i][j] += p.noise(j / 8.0, i / 8.0) * 0.125f;
				world[i][j] += p.noise(j / 16.0, i / 16.0) * 0.25f;
				world[i][j] += p.noise(j / 32.0, i / 32.0) * 0.5f;
				world[i][j] += p.noise(j / 64.0, i / 64.0) * 1.0f;
				world[i][j] += p.noise(j / 128.0, i / 128.0) * 2.0f;

				world[i][j] += (1.5f - (0.008 * i));

				world[i][j] /= 6;
				//world[i][j] += 1;
			}
		}

	 	return normalize(world);

	}

	private double gradient(int y){

		return 0.7 - (y * 1);

	}

	public int[][] makeTileable(double[][] array){

		int[][] newArr = new int[array.length][array[0].length];
		double[][] blockedArr = new double[array.length / 4][array[0].length / 4];

		for (int i = 0; i < blockedArr.length; i++){

			for (int j = 0; j < blockedArr[0].length; j++){

				blockedArr[i][j] = getHighestNeighbour(j * 4, i * 4, array);

			}
		}

		for (int i = 0; i < array.length; i++){

			for (int j = 0; j < array[0].length; j++){

				int x = (int)(blockedArr[i / 4][j / 4] * 255);

				if (x > 224){

					newArr[i][j] = 4;

				} else if (x > 192){

					newArr[i][j] = 3;

				} else if (x > 160){

					newArr[i][j] = 2;

				} else if (x > 128){

					newArr[i][j] = 1;

				} else if (x > 96){

					newArr[i][j] = 0;

				} else {

					newArr[i][j] = -1;

				}
			}
		}

		return newArr;
	}

	private double[][] normalize(double[][] array){

		double highest = Double.MIN_VALUE;
		double lowest = Double.MAX_VALUE;

		for (int i = 0; i < array.length; i++){

			for (int j = 0; j < array[0].length; j++){

				if (array[i][j] > highest) {highest = array[i][j];}
				if (array[i][j] < lowest) {lowest = array[i][j];}

			}
		}

		for (int i = 0; i < array.length; i++){

			for (int j = 0; j < array[0].length; j++){

				array[i][j] = (array[i][j] - lowest) / (highest - lowest);

			}
		}

		return array;
	}

	private double getHighestNeighbour(int x, int y, double[][] array){

		double highest = Double.MIN_VALUE;

		for (int i = (y - 1); i <= (y + 1); i++){

			for (int j = (x - 1); j <= (x + 1); j++){

				if (!(i == y && j == x)){

					try {
					
						if (array[i][j] > highest){ highest = array[i][j]; }

					} catch (Exception e) {}

				}
			}
		}

		return highest;
	}
}