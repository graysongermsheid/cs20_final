package level.worldgen;

import level.Level;

public class WorldGenerator {

	public Level generateWorld(int width, int height){

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

				world[i][j] /= 6;
				//world[i][j] += 1;
			}
		}

		double[][] grad = new double[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				grad[i][j] = gradient(j, i, width, height);

			}
		}

		grad = normalize(grad);
		world = normalize(world);

	 	for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				world[i][j] -= grad[i][j];

			}
		}

		int[][] q = makeTileable(bottom(world));
		
		boolean[][] water = new boolean[q.length][q[0].length];
		boolean[][] grass0 = new boolean[q.length][q[0].length];
		boolean[][] grass1 = new boolean[q.length][q[0].length];
		boolean[][] mountain0 = new boolean[q.length][q[0].length];
		boolean[][] mountain1 = new boolean[q.length][q[0].length];
		boolean[][] mountain2 = new boolean[q.length][q[0].length];
		
		Level l = new Level(width / 2, height / 2, "Overworld");
		l.addLayer("water.png");
		l.addLayer("sand.png");
		l.addLayer("grass.png");
		l.addLayer("mountain.png");
		l.addLayer("mountain_high.png");
		l.addLayer("mountain_high.png");
		
		for (int i = 0; i < height / 2; i++){
			
			for (int j = 0; j < width / 2; j++){
				
				water[i][j] = q[i*2][j*2] == -1;
				grass0[i][j] = q[i*2][j*2] >= 0;
				grass1[i][j] = q[i*2][j*2] >= 1;
				mountain0[i][j] = q[i*2][j*2] >= 2;
				mountain1[i][j] = q[i*2][j*2] >= 3;
				mountain2[i][j] = q[i*2][j*2] == 4;
				
			}
		}
		
		for (int i = 0; i < height / 2; i++){
			
			for (int j = 0; j < width / 2; j++){
				
				l.addTile(TileMapper.processTile(j, i, water, false), j, i, 0);
				l.addTile(TileMapper.processTile(j, i, grass0, false), j, i, 1);
				l.addTile(TileMapper.processTile(j, i, grass1, false), j, i, 2);
				l.addTile(TileMapper.processTile(j, i, mountain0, false), j, i, 3);
				l.addTile(TileMapper.processTile(j, i, mountain1, false), j, i, 4);
				l.addTile(TileMapper.processTile(j, i, mountain2, false), j, i, 5);
				
			}
		}
		
		return l;
	}
	
	public int[][] generateInts(int width, int height){

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

				world[i][j] /= 6;
				//world[i][j] += 1;
			}
		}

		double[][] grad = new double[height][width];

		for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				grad[i][j] = gradient(j, i, width, height);

			}
		}

		grad = normalize(grad);
		world = normalize(world);

	 	/*for (int i = 0; i < height; i++){

			for (int j = 0; j < width; j++){

				world[i][j] -= grad[i][j];

			}
		}*/
	 	
	 	return makeTileable(world);
	}
	
	public double[][] getRaw(int width, int height){
		
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

				world[i][j] /= 6;
				//world[i][j] += 1;
			}
		}
		
		return normalize(world);
	}

	private double[][] bottom(double[][] in){

		for (int i = 0; i < in.length; i++){

			for (int j = 0; j < in[0].length; j++){

				if (in[i][j] < 0){ in[i][j] = 0;}

			}
		}

		return in;
	}

	private double gradient(int x, int y, int width, int height){

		return 0.005 * Math.sqrt(Math.pow(y - (height / 2), 2) + Math.pow(x - (width / 2), 2));

	}

	public int[][] makeTileable(double[][] array){

		int[][] newArr = new int[array.length][array[0].length];
		double[][] blockedArr = new double[array.length / 2][array[0].length / 2];

		for (int i = 0; i < blockedArr.length; i++){

			for (int j = 0; j < blockedArr[0].length; j++){

				blockedArr[i][j] = getHighestNeighbour(j * 2, i * 2, array);

			}
		}

		for (int i = 0; i < array.length; i++){

			for (int j = 0; j < array[0].length; j++){

				int x = (int)(blockedArr[i / 2][j / 2] * 255);

				if (x > 210){

					newArr[i][j] = 4;

				} else if (x > 168){

					newArr[i][j] = 3;

				} else if (x > 126){

					newArr[i][j] = 2;

				} else if (x > 64){

					newArr[i][j] = 1;

				} else if (x > 42){

					newArr[i][j] = 0;

				} else {

					newArr[i][j] = -1;

				}

				if ((i <= 1) || (j <= 1) || (i >= array.length - 2) || (j >= array[0].length - 2)){

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