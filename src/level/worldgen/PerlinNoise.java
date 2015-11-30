package level.worldgen;

import java.util.Random;

public class PerlinNoise {
	
	private int[] permutations;
	private final double[][] gradients = {{1.0, 1.0}, {-1.0, 1.0}, {1.0, -1.0}, {-1.0, -1.0}};

	public PerlinNoise(){

		Random r = new Random();

		permutations = new int[512];

		for (int i = 0; i < 256; i++){permutations[i] = -1;}

		for (int i = 0; i < 256; i++){

			int j;

			do {

				j = r.nextInt(256);

			} while(permutations[j] != -1);

			permutations[j] = i;
			permutations[256 + j] = i;
		}
	}

	public PerlinNoise(int seed){

		Random r = new Random(seed);

		permutations = new int[512];

		for (int i = 0; i < 256; i++){permutations[i] = -1;}

		for (int i = 0; i < 256; i++){

			int j;

			do {

				j = r.nextInt(256);

			} while(permutations[j] != -1);

			permutations[j] = i;
			permutations[256 + j] = i;
		}
	}

	public double noise(double x, double y){

		//get containing grid tile
		int X = fastFloor(x);
		int Y = fastFloor(y);

		//get relative position;
		x = x - X;
		y = y - Y;

		X = X & 255;
		Y = Y & 255;

		double[] grad00 = gradients[permutations[X + permutations[Y]] % 4];
		double[] grad01 = gradients[permutations[X + permutations[Y + 1]] % 4];
		double[] grad10 = gradients[permutations[X + 1 + permutations[Y]] % 4];
		double[] grad11 = gradients[permutations[X + 1 + permutations[Y + 1]] % 4];

		double n00 = dot(grad00, new double[] {x, y});
		double n01 = dot(grad01, new double[] {x, y - 1});
		double n10 = dot(grad10, new double[] {x - 1, y});
		double n11 = dot(grad11, new double[] {x - 1, y - 1});

		double fadeX = fade(x);
		double fadeY = fade(y);

		double X0 = interpolate(n00, n10, fadeX);
		double X1 = interpolate(n01, n11, fadeX);

		double z = interpolate(X0, X1, fadeY);

		return z;
	}

	private double fade(double t){

		return t*t*t*(t*(t*6-15)+10);

	}

	private double interpolate(double a, double b, double t){

		return (1 - t)*a + t*b;

	}

	private int fastFloor(double x){

		return (x > 0) ? (int)x : (int)x - 1;

	}

	private double dot(double[] a, double[] b){

		return (a[0] * b[0]) + (a[1] * b[1]);
		
	}
}