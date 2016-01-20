package game;

public class ScoreTracker {

	public static int playerHealth;
	public static int score;
	public static int goldCollected;
	public static int healthLost;
	public static int healthHealed;
	public static int areasExplored;
	public static int monstersKilled;
	public static double distanceTraveled;
	
	public static void resetStats(){
		

		score = 0;
		goldCollected = 0;
		healthLost = 0;
		healthHealed = 0;
		areasExplored = 0;
		distanceTraveled = 0;
		monstersKilled = 0;
		
	}
}
